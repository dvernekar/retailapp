package com.dbr.retail.server.error;

public class RestPreconditions {

	public static <T> T checkNotNull(T reference, String msgKey) throws Exception { 
        if (reference == null) { 
            throw new BadRequestException(msgKey + " must not be null."); 
        } 
        return reference; 
    } 
	
	
	public static <T> T checkFound(T reference) throws Exception {
		if (reference == null) { 
            throw new BadRequestException(" Entity doesn't exist"); 
        } 
        return reference; 
	}
}
