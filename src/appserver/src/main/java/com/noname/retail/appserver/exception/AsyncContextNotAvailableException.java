package com.noname.retail.appserver.exception;

public class AsyncContextNotAvailableException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -2773855531975451235L;

    public AsyncContextNotAvailableException() {
        super();
    }
    
    public AsyncContextNotAvailableException(String message) {
        super(message);
    }
    
}
