package com.noname.retail.appserver.messagelistener;

import com.noname.retail.appserver.exception.NgnmsServiceException;
import com.noname.retail.appserver.model.ServiceRequest;

public interface ITargetMessageProcessor {
	
	public void processMessage(ServiceRequest serviceRequest) throws NgnmsServiceException;

}
