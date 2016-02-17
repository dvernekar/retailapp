package com.noname.retail.appserver.serviceclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.noname.retail.appserver.exception.RetailServiceException;
import com.noname.retail.appserver.model.ServiceRequest;
import com.noname.retail.appserver.model.ServiceResponse;
import com.noname.retail.appserver.processor.IRequestProcessor;
import com.noname.retail.appserver.processor.RequestProcessor;
import com.noname.retail.usermgmt.vo.UserVO;
import com.noname.retail.util.NGServerUtils;

public class AsyncUserMgmtServiceImpl implements IAsyncService {

	/*@Autowired
	private IRequestProcessor requestProcessor;*/

	@Override
	public void processRequest(ServiceRequest serviceRequest) throws RetailServiceException {
		// TODO Auto-generated method stub
		ServiceResponse response  = new ServiceResponse ();
		if(serviceRequest.getOperation() == NGServerUtils.READ_ALL){
			//TODO get via user interface
			UserVO user1 = new UserVO();
			user1.setUserName("first");
			user1.setPassword("fisrt");
			
			UserVO user2 = new UserVO();
			user2.setUserName("second");
			user2.setPassword("second");
			
			List<UserVO> userList = new ArrayList<UserVO>();
			userList.add(user1);userList.add(user2);
			response.setRequestType(serviceRequest.getRequestType());
			response.setData(userList);
			response.setCorrelationID(serviceRequest.getRequestID());
			response.setStatus("Success");
			
			IRequestProcessor requestProcessor = new RequestProcessor();
			requestProcessor.processResponse(response);
		}
		
	}

	


}
