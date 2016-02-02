package com.noname.retail.appserver.login;

import org.springframework.security.core.AuthenticationException;

public class NGAuthenticationException extends AuthenticationException {

	/**
     * 
     */
    private static final long serialVersionUID = -5029562829462663291L;

	public NGAuthenticationException(String msg) {
	    super(msg);
    }

}
