
package com.noname.retail.appserver.processor;

import javax.servlet.AsyncContext;

import org.atmosphere.cpr.AtmosphereResource;
import org.springframework.web.context.request.async.DeferredResult;

import com.noname.retail.appserver.model.NGServerResponse;
import com.noname.retail.appserver.model.ServiceRequest;
import com.noname.retail.appserver.model.ServiceResponse;
/**
 * 
 * Interface for the Request Processor
 * This contains all the method signatures,which controllers will use for 
 * their sync and async requests processing.
 *
 */
public interface IRequestProcessor {
	/**
	 * 
	 * @param request
	 * @return {@link NGServerResponse}
	 */
	public NGServerResponse processSyncRequest(ServiceRequest request);
	/**
	 * 
	 * @param dr
	 * @param request
	 */
	public void processAsyncRequest(DeferredResult<NGServerResponse> dr, ServiceRequest request);
	/**
	 * 
	 * @param aContext
	 * @param request
	 */
	public void processAsyncRequest(AsyncContext aContext, ServiceRequest request);
	/**
	 * 
	 * @param atmosphere
	 * @param request
	 */
	public void processSSERequest(AtmosphereResource atmosphere, ServiceRequest request);
	/**
	 * 
	 * @param response
	 */
	public void processResponse(ServiceResponse response);
	
	
	
	
}
