package com.noname.retail.appserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.noname.retail.appserver.model.NGServerResponse;
import com.noname.retail.appserver.model.ServiceRequest;
import com.noname.retail.appserver.processor.IRequestProcessor;
import com.noname.retail.appserver.processor.RequestProcessor;
import com.noname.retail.logger.RetailLogger;
import com.noname.retail.usermgmt.vo.UserVO;


@Controller
@RequestMapping("/api/usermgmt")
public class UserMgmtController {
	
	static private Logger _logger = (Logger) LoggerFactory.getLogger(UserMgmtController.class);
	
	/*@Autowired
	private IRequestProcessor requestProcessor;



	public void setRequestProcessor(IRequestProcessor requestProcessor) {
		this.requestProcessor = requestProcessor;
	}

	public IRequestProcessor getRequestProcessor() {
		return requestProcessor;
	}
*/
	@RequestMapping(method = RequestMethod.GET, value = "/users", produces="application/json")
	@ResponseBody
	public DeferredResult<NGServerResponse> getAllOVusers(HttpServletRequest hsrequest, HttpServletResponse hsresponse) {
		RetailLogger.debug(_logger,"UserMgmtController : getAllOVusers : begin");
		DeferredResult<NGServerResponse> dr = new DeferredResult<NGServerResponse>();
		UserVO ovRequestObject =new UserVO();
		ServiceRequest<UserVO> request = new ServiceRequest<UserVO>
		(NGServerUtils.getServiceName(hsrequest.getRequestURL()), NGServerUtils.READ_ALL,NGServerUtils.ASYNC_SINGLE, ovRequestObject);
		ovRequestObject.setUniqueRequestId(request.getRequestID());
		request.setRequest(ovRequestObject);
		IRequestProcessor requestProcessor = new RequestProcessor();
		requestProcessor.processAsyncRequest(dr, request);
		return dr;
	}

}
