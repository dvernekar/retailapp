/*
    $Id:
    Copyright (c) 1997-2013 Alcatel-Lucent. All Rights Reserved.
    No part of this file may be reproduced, stored in a retrieval system,
    or transmitted in any form or by any means, electronic, mechanical,
    photocopying, or otherwise without the prior permission of Alcatel-Lucent.
*/
package com.noname.retail.appserver.messagelistener;

import com.noname.retail.appserver.exception.NgnmsServiceException;
import com.noname.retail.appserver.model.ServiceRequest;
import com.noname.retail.appserver.model.ServiceResponse;

public interface IMessageListener {

	public void postRequest(ServiceRequest serviceRequest) throws NgnmsServiceException;
	public void postResponse(ServiceResponse response) throws NgnmsServiceException;
}
