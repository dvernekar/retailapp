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
