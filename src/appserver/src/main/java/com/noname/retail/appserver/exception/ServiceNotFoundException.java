/*
    $Id:
    Copyright (c) 1997-2013 Alcatel-Lucent. All Rights Reserved.
    No part of this file may be reproduced, stored in a retrieval system,
    or transmitted in any form or by any means, electronic, mechanical,
    photocopying, or otherwise without the prior permission of Alcatel-Lucent.
*/
package com.noname.retail.appserver.exception;

public class ServiceNotFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -8575407684911915070L;

    public ServiceNotFoundException() {
        super();
    }
    
    public ServiceNotFoundException(String message) {
        super(message);
    }
}
