<<<<<<< HEAD
/*
    $Id:
    Copyright (c) 1997-2013 Alcatel-Lucent. All Rights Reserved.
    No part of this file may be reproduced, stored in a retrieval system,
    or transmitted in any form or by any means, electronic, mechanical,
    photocopying, or otherwise without the prior permission of Alcatel-Lucent.
*/
package com.noname.retail.appserver.messagelistener;

import com.noname.retail.appserver.exception.RetailServiceException;
import com.noname.retail.appserver.model.ServiceRequest;

public interface ITargetMessageProcessor {
	
	public void processMessage(ServiceRequest serviceRequest) throws RetailServiceException;

}
=======
package com.noname.retail.appserver.messagelistener;

import com.noname.retail.appserver.exception.NgnmsServiceException;
import com.noname.retail.appserver.model.ServiceRequest;

public interface ITargetMessageProcessor {
	
	public void processMessage(ServiceRequest serviceRequest) throws NgnmsServiceException;

}
>>>>>>> 11a1229f12cea8ecfacde40c22b9ece0005c62b5
