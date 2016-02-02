/*
    Copyright (c) 1997-2013 Alcatel-Lucent. All Rights Reserved.
    No part of this file may be reproduced, stored in a retrieval system,
    or transmitted in any form or by any means, electronic, mechanical,
    photocopying, or otherwise without the prior permission of Alcatel-Lucent.
 */
package com.noname.retail.appserver.login;
/**
 * Class to represent the returned data object after performing login api 
 * @version 1.0
 * @since Nov, 2013
 * @author Don Le
 *
 */
public class LoginResultObject {

    public static final String LOGIN_SUCCESS = "login.success";
    public static final String LICENSE_REQUEST_TIME_OUT = "login.license.request.timeout";
    public static final String BAD_CREDENTIALS = "login.wrongusernamepassword";
    public static final String NO_LICENSE = "login.nolicense";
    public static final String LOG_OUT = "login.logout";
    public static final String LICENSE_REQUEST_EXCEPTION = "license.request.exception";
    public static final String ACTIVEMQ_PERSISTENT_STORE_EXCEPTION = "activeMQ.store.exception";

//    private String status;
    private String message;
    private String accessToken;

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

    public LoginResultObject(String status, String message, String accessToken) {
//        this.status = status;
        this.message = message;
        this.accessToken = accessToken;
    }

    public LoginResultObject() {
    }

}
