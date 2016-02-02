
package com.noname.retail.appserver.serviceclient;

import com.noname.retail.appserver.exception.NgnmsServiceException;
import com.noname.retail.appserver.model.ServiceRequest;
import com.noname.retail.appserver.model.ServiceResponse;

public interface IAsyncService {

	  public void  processRequest(ServiceRequest serviceRequest) throws NgnmsServiceException;
	  public void  onMessage(ServiceResponse serviceResponse) throws NgnmsServiceException;

}
