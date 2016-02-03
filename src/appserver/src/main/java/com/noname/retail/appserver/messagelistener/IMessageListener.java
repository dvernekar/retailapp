package com.noname.retail.appserver.messagelistener;

import com.noname.retail.appserver.exception.NgnmsServiceException;
import com.noname.retail.appserver.model.ServiceRequest;
import com.noname.retail.appserver.model.ServiceResponse;

public interface IMessageListener {

	public void postRequest(ServiceRequest serviceRequest) throws NgnmsServiceException;
	public void postResponse(ServiceResponse response) throws NgnmsServiceException;
}
