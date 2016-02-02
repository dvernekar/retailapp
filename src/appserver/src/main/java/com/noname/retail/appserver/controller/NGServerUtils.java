/*
    $Id:
    Copyright (c) 1997-2013 Alcatel-Lucent. All Rights Reserved.
    No part of this file may be reproduced, stored in a retrieval system,
    or transmitted in any form or by any means, electronic, mechanical,
    photocopying, or otherwise without the prior permission of Alcatel-Lucent.
*/
package com.noname.retail.appserver.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noname.retail.logger.RetailLogger;
import com.noname.retail.util.OVVersionUtil;

public final class NGServerUtils {

	static private Logger _logger = (Logger) LoggerFactory.getLogger(NGServerUtils.class);

	//response status messages
	public static final String Success = "SUCCESS";
	public static final String Error = "ERROR";
	public static final String BAD_REQUEST = "BAD_REQUEST";
	
	
	//operations
	public static final int CREATE = 1;
	public static final int DEL = 2;
	public static final int READ = 3;
	public static final int UPDATE = 4;
	public static final int READ_ALL = 5;
	public static final int READ_CORE = 6;
	public static final int READ_USER_CURRENT = 7; // read current login user
	public static final int UPDATE2 = 8 ;
	public static final int UPLOAD = 9 ;
	public static final int DOWNLOAD = 10 ;
	public static final int GET_FILE = 11 ;
	public static final int PUSH_FILE = 12 ;
	public static final int CHECK_FILE=13;
	public static final int DELETE_FILE = 14;
	public static final int ASSIGN = 15;
	//request type
	public static final int ASYNC_SINGLE = 1;
	public static final int ASYNC_MULTI = 2;
	public static final int ASYNC_SSE = 3;
	
	// name of the fault response class returned by HC 
	public static final String OVFaultResponse = "OVFaultResponse";
	
	public static final String DiscoveryFaultResponse = "DiscoveryFaultResponse";
	
	public static final String OV_VERSION_HEADER = "Ov-App-Version";
	
	static Map<String,String> serviceNameMap  = new HashMap<String,String>();
	
	//helper methods
	public static String getServiceName(StringBuffer url) {
		try{
			if(serviceNameMap.containsKey(url.toString())){
				return serviceNameMap.get(url.toString());				
			}else{
				String[] s = url.toString().split("/");
				String serviceName = null;
				for (int i = 0; i < s.length && serviceName == null; i++) {
                    if("api".equals(s[i])) {
                        serviceName = s[i+1];
                    }
                }
				
				serviceNameMap.put(url.toString(), serviceName);
				s = null;
				return serviceName;
			}
		}catch(Exception ex){
			RetailLogger.error(_logger,"failed to return service name for the URL : "+url.toString());
		}
		return url.toString();
	}
	
	public static String getOVVersion(HttpServletRequest request){
		String version = request.getHeader(OV_VERSION_HEADER);
		return version != null ? request.getHeader(OV_VERSION_HEADER) : OVVersionUtil.ORIGINAL_VERSION;
	}
	
	
	
}
