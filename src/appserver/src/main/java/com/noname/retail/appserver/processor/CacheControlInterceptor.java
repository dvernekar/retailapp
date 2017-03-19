/*
    $Id: //depot/omnivista/ngnms/ovnms-e-421/src/framework/appserver/src/main/java/com/alu/ov/ngnms/appserver/utils/CacheControlInterceptor.java#1 $
    Copyright (c) 1997-2013 Alcatel-Lucent. All Rights Reserved.
    No part of this file may be reproduced, stored in a retrieval system,
    or transmitted in any form or by any means, electronic, mechanical,
    photocopying, or otherwise without the prior permission of Alcatel-Lucent.
 */
package com.noname.retail.appserver.processor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
/**
 * This class has role as an intercepter to attach the Cache control to the response header of all REST APIs
 * The result, browsers will not cache the REST response objects and we can ensure the user always see the latest values.
 * @author 
 *
 */
public class CacheControlInterceptor extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest arg0,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	    response.setHeader("Pragma","no-cache");
	    response.setDateHeader("Expires", 0);
		chain.doFilter(arg0, response);
	}

}
