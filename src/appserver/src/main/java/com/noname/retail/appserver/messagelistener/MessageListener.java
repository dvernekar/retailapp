/*
    $Id:
    Copyright (c) 1997-2013 Alcatel-Lucent. All Rights Reserved.
    No part of this file may be reproduced, stored in a retrieval system,
    or transmitted in any form or by any means, electronic, mechanical,
    photocopying, or otherwise without the prior permission of Alcatel-Lucent.
*/
package com.noname.retail.appserver.messagelistener;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.noname.retail.appserver.exception.NgnmsServiceException;
import com.noname.retail.appserver.model.ServiceRequest;
import com.noname.retail.appserver.model.ServiceResponse;
import com.noname.retail.appserver.serviceclient.IAsyncService;
import com.noname.retail.logger.RetailLogger;
import com.noname.retail.util.OVVersionUtil;

public class MessageListener implements IMessageListener {

	static private Logger _logger = (Logger) LoggerFactory
			.getLogger(MessageListener.class);

	private HashMap<String,Map<String, ITargetMessageProcessor>> targetServiceMap;
 
		
	ExecutorService singleResponseExecutor = null;

	private HashMap<String, Map<String, IAsyncService>> asyncServiceMap;

	String noofThreads;

	public String getNoofThreads() {
		return noofThreads;
	}


	public void setNoofThreads(String noofThreads) {
		this.noofThreads = noofThreads;
	}
	
	public HashMap<String, Map<String, ITargetMessageProcessor>> getTargetServiceMap() {
		return targetServiceMap;
	}
	
	public void setTargetServiceMap(
			HashMap<String, Map<String, ITargetMessageProcessor>> targetServiceMap) {
		this.targetServiceMap = targetServiceMap;
	}

	/**
	 *  init method to start executor with the provided thread count in appserver.properties
	 */
	public void init(){
		String 	threadsInPool =	noofThreads.trim();
		int numberOfThreads=Integer.parseInt(threadsInPool);
		singleResponseExecutor = Executors.newFixedThreadPool(numberOfThreads);
		RetailLogger.info(_logger, "MessageListener : executor starting with the thread count "+threadsInPool);
	}
	
	

	public void setAsyncServiceMap(
			HashMap<String, Map<String, IAsyncService>> asyncServiceMap) {
		this.asyncServiceMap = asyncServiceMap;
	}
	
	/* method to forward the response to request processor
	 * @see com.noname.retail.appserver.messagelistener.IMessageListener#postResponse(com.noname.retail.appserver.model.ServiceResponse)
	 */
	public void postResponse(ServiceResponse response) {		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Runnable worker = new PostMessagetoRequestProcessor(response);
		singleResponseExecutor.execute(new DelegatingSecurityContextRunnable(worker,securityContext));
		
	}

	class PostMessagetoRequestProcessor implements Runnable {

		ServiceResponse response = null;

		public PostMessagetoRequestProcessor(ServiceResponse response) {
			this.response = response;
		}

		@Override
		public void run() {
      
			IAsyncService asalservice = OVVersionUtil.retrieveMatchProcessor(response.getVersion(), OVVersionUtil.getVersionHistory(), asyncServiceMap.get(response.getServiceName()));
			try {
				RetailLogger.debug(_logger,"PostMessagetoRequestProcessor : response id :" + response.getCorrelationID());
				if (asalservice != null)
					asalservice.onMessage(response);
				else
				{
					RetailLogger.error(_logger,	"No service found ,response id ...." + response.getCorrelationID() +
							" service name " + response.getServiceName());
				}
			} catch (Exception e) {
				e.printStackTrace();

			}

		}
	}

	/* method to forward the request to respective message processor
	 * @see com.noname.retail.appserver.messagelistener.IMessageListener#postRequest(com.noname.retail.appserver.model.ServiceRequest)
	 */
	public void postRequest(ServiceRequest serviceRequest) 	throws NgnmsServiceException {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		RetailLogger.info(_logger, "post the message in queue");
		Runnable worker = new PostMessagetoTargetProcessor(serviceRequest);
		singleResponseExecutor.execute(new DelegatingSecurityContextRunnable(worker,securityContext));
	}
	
	
	class PostMessagetoTargetProcessor implements Runnable {

		ServiceRequest request = null;

		public PostMessagetoTargetProcessor(ServiceRequest request) {
			this.request = request;
		}

		@Override
		public void run() {
			ITargetMessageProcessor targetProcessor = OVVersionUtil.retrieveMatchProcessor(request.getVersion(), OVVersionUtil.getVersionHistory(), targetServiceMap.get(request.getTarget()));
			RetailLogger.debug(_logger, "targetProcessor :"+targetProcessor);
			if (targetProcessor != null)
			{
				try {
					targetProcessor.processMessage(request);
				} catch (NgnmsServiceException e) {
					RetailLogger.error(_logger, "Error Searching Target ...");
					e.printStackTrace();
				}
			}
			else
			{
				RetailLogger.error(_logger, "Searching Unknown Target ...");
			}
		}
	}

	/**
	 * Wraps a delegate Runnable with logic for setting up a SecurityContext
	 * before invoking the delegate Runnable and then removing the
	 * SecurityContext after the delegate has completed.
	 */
	private class DelegatingSecurityContextRunnable implements Runnable {
		private SecurityContext m_ctx;
		private Runnable m_delegate;

		public DelegatingSecurityContextRunnable(Runnable delegate, SecurityContext context) {
			this.m_delegate = delegate;
			this.m_ctx = context;
		}

		@Override
		public void run() {
			try {
				SecurityContextHolder.setContext(m_ctx);
				m_delegate.run();
			} finally {
				SecurityContextHolder.clearContext();
			}
		}

	}
}
