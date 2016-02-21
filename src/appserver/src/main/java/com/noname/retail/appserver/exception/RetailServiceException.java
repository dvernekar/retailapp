package com.noname.retail.appserver.exception;

public class RetailServiceException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 735100736075504751L;

    public RetailServiceException() {
        super();
    }
    
    public RetailServiceException(String message) {
        super(message);
    }
}
