package com.noname.retail.appserver.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListenerAdapter;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.context.request.async.DeferredResult;

import com.noname.retail.appserver.controller.NGServerUtils;
import com.noname.retail.appserver.exception.AsyncContextNotAvailableException;
import com.noname.retail.appserver.exception.NgnmsServiceException;
import com.noname.retail.appserver.exception.ServiceNotFoundException;
import com.noname.retail.appserver.model.NGServerResponse;
import com.noname.retail.appserver.model.ServiceRequest;
import com.noname.retail.appserver.model.ServiceResponse;
import com.noname.retail.logger.RetailLogger;
import com.noname.retail.appserver.serviceclient.IAsyncService;
import com.noname.retail.appserver.serviceclient.ISyncService;
import com.noname.retail.util.OVVersionUtil;


/**
 * Processes all the requests from Controllers
 * 
 *
 */

public class RequestProcessor implements IRequestProcessor {
	
	static private Logger _logger = (Logger) LoggerFactory.getLogger(RequestProcessor.class);

	private static Map<String, AsyncContext> asynchContextMap = new HashMap<String, AsyncContext>();

	private static Map<String, DeferredResult<NGServerResponse>> deferredResultMap = new HashMap<String, DeferredResult<NGServerResponse>>();

	private static Map<String, Broadcaster> broadcasterMap = new HashMap<String, Broadcaster>();

	private HashMap<String, Map<String, IAsyncService>> asynchServiceMap;

	private ObjectMapper mapper = new ObjectMapper();
	
	private HashMap<String, Map<String, ISyncService>> synchServiceMap;
	
	
	public void setAsynchServiceMap(
			HashMap<String, Map<String, IAsyncService>> asynchServiceMap) {
		this.asynchServiceMap = asynchServiceMap;
	}
	
	public void setSynchServiceMap(
			HashMap<String, Map<String, ISyncService>> synchServiceMap) {
		this.synchServiceMap = synchServiceMap;
	}

	private WeakHashMap<Broadcaster,OnDisconnectListener> listenerMap = new WeakHashMap<Broadcaster,OnDisconnectListener>();


	/*
	 * processing of Synchronous request (non-Javadoc)
	 * All the synchronous requests from the controllers
	 * will be handled.
	 * The sync service name is extracted from service request
	 * Using the service name service is picked from the syncservice map
	 * The request is passed to that particular service for processing.
	 * 
	 * @see
	 * com.noname.retail.appserver.processor.IRequestProcessor#processSyncRequest
	 * (com.noname.retail.appserver.model.ServiceRequest)
	 */
	public NGServerResponse processSyncRequest(ServiceRequest request) {
		ServiceResponse response = null ;
		try {
			String serviceName = request.getServiceName();
			RetailLogger.info(_logger,"serviceName is " + serviceName);

			ISyncService syncService = OVVersionUtil.retrieveMatchProcessor(request.getVersion(), OVVersionUtil.getVersionHistory(), synchServiceMap.get(serviceName));
			if (syncService != null)
			{
				 response = syncService.processRequest(request);
				 RetailLogger.info(_logger,"the return from the service is "
							+ response.toString());
				 
				if(response.getData()!=null){
					NGServerResponse resp = new NGServerResponse(response.getStatus(),getStatusCode(response.getStatus(),response.getOperation()),
					response.getData().getClass().getSimpleName(),response.getData());
					return resp;
				}
			} else {
				RetailLogger.warn(_logger," Cannot found syncService! ");
			}

		} catch (Exception e) {
			NGServerResponse resp = new NGServerResponse(NGServerUtils.Error , e.getMessage());
			resp.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			RetailLogger.error(_logger,"Error when processing request. ", e);
			return resp;
		}

		return null;
	}

	/*
	 * processing of Asynchronous request (single response) (non-Javadoc)
	 * Single requests are being processed using the Deferred Result.
	 * A unique id is generated corresponding to each single async request and
	 * is stored in the deferredResultMap.
	 * The service name is extracted from the service request
	 * Using the service name service is picked from the asynchservice map
	 * The request is passed to that particular service for processing.
	 * 
	 * @see
	 * com.noname.retail.appserver.processor.IRequestProcessor#processAsyncRequest
	 * (org.springframework.web.context.request.async.DeferredResult,
	 * java.lang.Object, javax.servlet.http.HttpServletRequest)
	 */
		public void processAsyncRequest(DeferredResult<NGServerResponse> dr,ServiceRequest request) {
			try {

				UUID uid = UUID.randomUUID();
				RetailLogger.debug(_logger,"RequestProcessor : processAsyncRequest : processing request with the request-id"+uid.toString());
				deferredResultMap.put(uid.toString(), dr);

				String serviceName = request.getServiceName();

				IAsyncService asalServices = OVVersionUtil.retrieveMatchProcessor(request.getVersion(), OVVersionUtil.getVersionHistory(), asynchServiceMap.get(serviceName));

				if (null == asalServices) {
					RetailLogger.error(_logger,"RequestProcessor : processAsyncRequest : Service not found for the service name "+serviceName);
					throw new ServiceNotFoundException("RequestProcessor : processAsyncRequest : Service not found for the service name "+serviceName);
				}

				request.setRequestID(uid.toString());

				asalServices.processRequest(request);
			}

			catch (ServiceNotFoundException e) {

				RetailLogger.error(_logger,"Service is not found. ", e);

			} catch (NgnmsServiceException e) {
			
				RetailLogger.error(_logger,"Error when proccessing request. ", e);
			}

		}

	/*
	 * processing of Asynchronous request (multi response) (non-Javadoc)
	 * A unique id is generated corresponding to each multi response async request and
	 * is stored in the asynchContextMap.
	 * The service name is extracted from the service request
	 * Using the service name service is picked from the asynchservice map
	 * The request is passed to that particular service for processing.
	 * 
	 * @see
	 * com.noname.retail.appserver.processor.IRequestProcessor#processAsyncRequest
	 * (javax.servlet.AsyncContext,
	 * com.noname.retail.appserver.model.ServiceRequest)
	 */
	public void processAsyncRequest(AsyncContext context, ServiceRequest request) {

		try {
			UUID uid = UUID.randomUUID();RetailLogger.info(_logger,"RequestProcessor : processAsyncRequest : processing " +
					"request (multi response) with the request-id "+uid.toString());
			
			asynchContextMap.put(uid.toString(), context);

			String serviceName = request.getServiceName();

			IAsyncService asalServices = OVVersionUtil.retrieveMatchProcessor(request.getVersion(), OVVersionUtil.getVersionHistory(), asynchServiceMap.get(serviceName));

			request.setRequestID(uid.toString());

			asalServices.processRequest(request);
		} catch (Exception e) {
			RetailLogger.error(_logger,"Error when processing request. ", e);
		}

	}

	/*
	 * processing the Server sent event request (non-Javadoc)
	 * A Broadcaster map is being prepared which stores the broadcaster references
	 * corresponding to the URLs to which the response is to be returned back.
	 * Atmosphere resource sent from the controller is passed to the Broadcaster
	 * The service name is extracted from the service request
	 * Using the service name service is picked from the asynchservice map
	 * The request is passed to that particular service for processing.
	 * 
	 * @see
	 * com.noname.retail.appserver.processor.IRequestProcessor#processSSERequest
	 * (org.atmosphere.cpr.AtmosphereResource,
	 * com.noname.retail.appserver.model.ServiceRequest)
	 */
	public void processSSERequest(AtmosphereResource atmoResource, ServiceRequest request) {

		String requestUrl = request.getRequestUrl();
		RetailLogger.info(_logger,"RequestProcessor : processSSERequest : processing request using with the url "+requestUrl);

		Broadcaster broadcaster = broadcasterMap.get(requestUrl);
		
		//Restore SecurityContext
		HttpSession session = atmoResource.getRequest().getSession(false);
		SecurityContext securityContext = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		SecurityContextHolder.setContext(securityContext);
		
		if(broadcaster==null) {

			broadcaster = BroadcasterFactory.getDefault().get();
			broadcasterMap.put(requestUrl, broadcaster);
		}

		OnDisconnectListener listener =listenerMap.get(broadcaster);

		if (listener==null){
			listener = new OnDisconnectListener(broadcaster);
			listenerMap.put(broadcaster,listener);
		}

		broadcaster.addAtmosphereResource(atmoResource);
		atmoResource.addEventListener(listener);

		try {
			String serviceName = request.getServiceName();

			 RetailLogger.info(_logger,"RequestProcessor : processSSERequest : serviceName from the SSEResponse " + serviceName);

			IAsyncService asyncServices = OVVersionUtil.retrieveMatchProcessor(request.getVersion(), OVVersionUtil.getVersionHistory(), asynchServiceMap.get(serviceName));

			asyncServices.processRequest(request);

			
		} catch (NgnmsServiceException ne) {
			RetailLogger.error(_logger,"Error while processing the server sent event"+ne.getMessage());
		} catch (Exception e) {
			RetailLogger.error(_logger,"Error while processing the server sent event"+e.getMessage());
		}

	}




	/*
	 * When the browser is shut down stop broadcasting
	 * And remove the atmosphere resource
	 * 
	 */
	private static final class OnDisconnectListener extends	AtmosphereResourceEventListenerAdapter {

		private final Broadcaster broadcaster;
		public OnDisconnectListener(Broadcaster broadcaster) {
			 RetailLogger.info(_logger,"inside on disconnect");
			this.broadcaster = broadcaster;
		}

		@Override
		public void onDisconnect(AtmosphereResourceEvent event) {

		 RetailLogger.info(_logger,"===== my onDisconnect, closing the connection =====");
			broadcaster.removeAtmosphereResource(event.getResource());
		}


	}

	/*
	 * processes the response received from the messaging framework
	 * it processes both the SINGLE and MULTI responses
	 * Single response received is set into the deferredResult.
	 * 
	 * Multi response is received in parts and each part is written
	 * onto the stream and final response is being checked.
	 * once the final response is received asynchronous context gets 
	 * completed and is removed from the Map maintained to store the same 
	 * 
	 * @see
	 * com.noname.retail.appserver.processor.IRequestProcessor#processResponse
	 * (java.lang.String, com.noname.retail.appserver.model.ServiceResponse)
	 */
	public void processResponse(ServiceResponse response) {
		
		String uid=null;
		if(null!=response.getCorrelationID()){
		   uid=response.getCorrelationID();
		}
		
		/** handle async single responses **/
		if (response.getRequestType() == NGServerUtils.ASYNC_SINGLE) {
			try{
				/** get deferred result object by passing the response-id **/
				RetailLogger.debug(_logger,"RequestProcessor : processResponse : processing single chunk response for the request-id  = "+ uid);
				DeferredResult<NGServerResponse> dr = deferredResultMap.get(uid);
				if(null!=dr && response.getData()!=null){			
					NGServerResponse resp = new NGServerResponse(response.getStatus(),getStatusCode(response.getStatus(),response.getOperation()),
							response.getData().getClass().getSimpleName(),response.getData());

					dr.setResult(resp);
					
				} 
				else {
					RetailLogger.error(_logger,"RequestProcessor : processResponse : response is null for the request-id  : "+ uid);
					throw new NgnmsServiceException("Response received from Service is NULL");
				}
			}
			catch(NgnmsServiceException e){
				response.setData(e.getMessage());
				processErrorResponse(response);
			}finally{
				/** deleting deferred result from the map after processing response **/
				 deferredResultMap.remove(uid);
			}
		}
		
		/** handle async multi responses **/
		else if (response.getRequestType() == NGServerUtils.ASYNC_MULTI) {
			AsyncContext ctx = asynchContextMap.get(uid);
			try {				
				if (null == ctx) {
					RetailLogger.error(_logger,"RequestProcessor : processResponse : asyncontext is null for the request-id : "+ uid);
					throw new AsyncContextNotAvailableException("Async context not found for the uid" + ctx);
				} else {
					RetailLogger.debug(_logger,"RequestProcessor : processResponse : processing multi-chunk response for the request-id : "+ uid);
					ObjectMapper jsonMapper = new ObjectMapper();
					Object data = response.getData();
					if (data != null) {
						NGServerResponse resp = new NGServerResponse(response.getStatus(),getStatusCode(response.getStatus(),
								response.getOperation()),response.getData().getClass().getSimpleName(),response.getData());
						String strResponse = jsonMapper.writeValueAsString(resp);
						RetailLogger.debug(_logger,"RequestProcessor : processResponse : JSON data chunk to be writen on stream : "	+ strResponse);
						ServletResponse servletResponse = ctx.getResponse();
						servletResponse.setContentType("application/json");
						servletResponse.setCharacterEncoding("UTF-8");
						PrintWriter writer = servletResponse.getWriter();
						if (writer == null) {
							throw new Exception("Writer is null in Servlet Response");
						}
						writer.write(strResponse);
						writer.flush();
						servletResponse.flushBuffer();				
					} else {
						// throw NGServiceException iso of generic exception
						throw new NgnmsServiceException("Response received from Service is NULL");
					}
				}
			}  catch (NgnmsServiceException e) {
				response.setData(e.getMessage());
				processErrorResponse(response);
			} catch (JsonGenerationException e) {
				response.setData(e.getMessage());
				processErrorResponse(response);
			} catch (JsonMappingException e) {
				response.setData(e.getMessage());
				processErrorResponse(response);
			} catch (IOException e) {
				response.setData(e.getMessage());
				processErrorResponse(response);
			} catch (Exception e) {
				e.printStackTrace();
				response.setData(e.getMessage());
				processErrorResponse(response);

			}finally{
				if (response.isFinal()) {
					RetailLogger.debug(_logger,"RequestProcessor : processResponse : calling ctx.complete  for the request-id : "+uid);
					/** close the context after receiving final response **/
					ctx.complete();
					/** deleting asynch context from the map after processing the response **/
					asynchContextMap.remove(uid);
				}
			}

		}
		/** handle SSE responses **/
		else if (response.getRequestType() == NGServerUtils.ASYNC_SSE) {
			try {
				 Object data = response.getData();
				 RetailLogger.info(_logger,"RequestProcessor : processResponse : got the response ===>"+data);
				 Broadcaster broadcaster = broadcasterMap.get(response.getRequestUrl());
				 RetailLogger.info(_logger,"Broadcaster reference inside the processSSEresponse"+broadcaster);
				broadcaster.broadcast(mapper.writeValueAsString(data));
			} catch (Exception e) {
				 RetailLogger.error(_logger,"RequestProcessor : processResponse : Exception in processing response "+ e.getMessage());
				 response.setData(e.getMessage());
				 processErrorResponse(response);
			}
		}

	}
  
	
	/*
	 * Processes the error responses when any exception is thrown from the processResponse.
	 * It processes the error responses for SINGLE, MULTI-PART and SSE responses.
	 * 
	 */
	public void processErrorResponse(ServiceResponse response) {

		String uid=null;
		if(null!=response.getCorrelationID()){
			uid=response.getCorrelationID();
		}

		try {
			if (response.getRequestType() == NGServerUtils.ASYNC_SINGLE) {
				DeferredResult<NGServerResponse> dr = deferredResultMap.get(uid);
				RetailLogger.info(_logger,"deferredResult Obj in map = " + dr);

				NGServerResponse resp = new NGServerResponse(NGServerUtils.Error, response.getErrorMessage());
				dr.setErrorResult(resp);
			} else if (response.getRequestType() == NGServerUtils.ASYNC_MULTI) {
				AsyncContext ctx = asynchContextMap.get(uid);
				ObjectMapper jsonMapper = new ObjectMapper();
				Object data = response.getErrorMessage();
				if (data != null) {
					String strResponse = jsonMapper.writeValueAsString(response.getErrorMessage());
					ServletResponse sresponse = ctx.getResponse();
					if (sresponse != null) {
						sresponse.getWriter().write(strResponse);
					}
					ctx.complete();
					asynchContextMap.remove(uid);
					
				}
			}

			else if (response.getRequestType() == NGServerUtils.ASYNC_SSE) {
				RetailLogger.info(_logger,"processing SSE response");
				Broadcaster broadcaster = broadcasterMap.get(response
						.getRequestUrl());
				RetailLogger.info(_logger,"Broadcaster reference inside the processSSEresponse"+broadcaster);
				broadcaster.broadcast("Error Occured while Server Sent event");
			}

		} catch (Exception e) {
			RetailLogger.info(_logger,"ERROR: Occured in processing Error Response ");
			e.printStackTrace();
		}
	}
	
	/**
	 * method to send response back to UI without processing further, if the provided input values are not valid
	 * @param response
	 * @param ctx
	 * @param httpStatusCode
	 */
	public void processInvalidMultiRequest(ServiceResponse response,AsyncContext ctx,int httpStatusCode) {
	try {
			RetailLogger.debug(_logger,"processing multi-chunk response with asynchcontext = "+ ctx);
			ObjectMapper jsonMapper = new ObjectMapper();
			Object data = response.getData();
			if (data != null) {
				NGServerResponse resp = new NGServerResponse(response.getStatus(),httpStatusCode,response.getData().getClass().getSimpleName(),
						response.getData());
				String strResponse = jsonMapper.writeValueAsString(resp);
				RetailLogger.debug(_logger,"RequestProcessor : processInvalidMultiRequest : JSON data to be writen on stream :"	+ strResponse);
				ServletResponse servletResponse = ctx.getResponse();
				servletResponse.setContentType("application/json");
				PrintWriter writer = servletResponse.getWriter();
				if (writer == null) {
					throw new Exception("Writer is null in Servlet Response");
				}
				writer.write(strResponse);
				writer.flush();
				servletResponse.flushBuffer();
				ctx.complete();

			} else {
				// throw NGServiceException iso of generic exception
				throw new NgnmsServiceException("Response received from Service is NULL");
			}

		}  catch (NgnmsServiceException e) {
			response.setData(e.getMessage());
		} catch (JsonGenerationException e) {
			response.setData(e.getMessage());
		} catch (JsonMappingException e) {
			response.setData(e.getMessage());
		} catch (IOException e) {
			response.setData(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			response.setData(e.getMessage());

		}

	}

	
	/**
	 * method to return standard HTTP code based on status and type of operation
	 * @param status
	 * @param typeOfCall
	 * @return
	 */
	private int getStatusCode(String status,int typeOfCall){
		int statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		RetailLogger.debug(_logger,"RequestProcessor : getStatusCode : status : "+status+" operation type :"+typeOfCall);
		if(NGServerUtils.Success.equalsIgnoreCase(status)){
			switch(typeOfCall){
			case NGServerUtils.CREATE:
				statusCode = HttpServletResponse.SC_CREATED;
				break;
			case NGServerUtils.DEL : 
				statusCode = HttpServletResponse.SC_OK;
				break;
			case  NGServerUtils.READ : 
				statusCode = HttpServletResponse.SC_OK;
				break;
			case NGServerUtils.UPDATE : 
				statusCode = HttpServletResponse.SC_OK;
				break;
			case NGServerUtils.READ_ALL :
				statusCode = HttpServletResponse.SC_OK;
				break;
			case NGServerUtils.READ_CORE : 
				statusCode = HttpServletResponse.SC_OK;
				break;
			case NGServerUtils.READ_USER_CURRENT: 
				statusCode = HttpServletResponse.SC_OK;
				break;
			case NGServerUtils.UPDATE2 : 
				statusCode = HttpServletResponse.SC_OK;
				break;
			case NGServerUtils.DOWNLOAD : 
				statusCode = HttpServletResponse.SC_OK;
				break;
			case NGServerUtils.UPLOAD : 
				statusCode = HttpServletResponse.SC_OK;
				break;
			default:
				System.out.println("Operation of response was not set.");
				// set default response status code to 200 in case some module operation forget to set operation property of the response. 
				statusCode = HttpServletResponse.SC_OK;
				break;
			}
			return statusCode;
		// in case the response status is not NGServerUtils.Success, set response status code to 500
		}else if(NGServerUtils.BAD_REQUEST.equalsIgnoreCase(status)){			
			return HttpServletResponse.SC_BAD_REQUEST;
		}else{
			return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		}
	}
	
	
	
}
