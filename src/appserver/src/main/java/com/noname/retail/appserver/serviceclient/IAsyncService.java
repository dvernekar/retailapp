
package com.noname.retail.appserver.serviceclient;

import com.noname.retail.appserver.exception.RetailServiceException;
import com.noname.retail.appserver.model.ServiceRequest;

public interface IAsyncService {

	  public void  processRequest(ServiceRequest serviceRequest) throws RetailServiceException;
}
