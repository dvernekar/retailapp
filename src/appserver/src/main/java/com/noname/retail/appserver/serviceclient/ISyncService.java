
package com.noname.retail.appserver.serviceclient;

import com.noname.retail.appserver.model.ServiceRequest;
import com.noname.retail.appserver.model.ServiceResponse;

public interface ISyncService {

	public ServiceResponse processRequest(ServiceRequest request);

}
