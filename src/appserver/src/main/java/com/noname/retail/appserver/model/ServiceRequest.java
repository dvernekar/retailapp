package com.noname.retail.appserver.model;

import java.util.HashMap;
import java.util.Map;

import com.noname.retail.util.OVVersionUtil;

public class ServiceRequest<T> {

	private String serviceName;

	private String requestID;

	private int operation;

	private int requestType;

	private T request;

	private String requestUrl;

	private String target;

	private String version = OVVersionUtil.ORIGINAL_VERSION;

	private Map<String, Object> additionalParams = new HashMap<String, Object>();

	/**
	 *  variable to specify response waiting period after placing request to JMS
	 */
	private long responseTimeout = 20000;



	public ServiceRequest(){}

    public ServiceRequest(String serviceName, int operation, T request){
    	this.serviceName = serviceName;
    	this.operation = operation;
    	this.request = request;
    }

    public ServiceRequest(String serviceName, int operation, int requestType, T request){
    	this.serviceName = serviceName;
    	this.operation = operation;
    	this.request = request;
    	this.requestType = requestType;
    }

    public ServiceRequest(String serviceName, int operation, int requestType, T request, String requestUrl){
    	this.serviceName = serviceName;
    	this.operation = operation;
    	this.request = request;
    	this.requestType = requestType;
    	this.requestUrl=requestUrl;
    }

    public ServiceRequest(String serviceName, int operation, int requestType, T request, String requestUrl,String target){
    	this.serviceName = serviceName;
    	this.operation = operation;
    	this.request = request;
    	this.requestType = requestType;
    	this.requestUrl=requestUrl;
    	this.target=target;
    }

    /**
     * constructor to set specific response timeout period for each request. This helps in having specific response wait time, after placing request at JMS Queue
     * @param serviceName
     * @param operation
     * @param requestType
     * @param request
     * @param responseTimeout
     */
    public ServiceRequest(String serviceName, int operation, int requestType, T request, long responseTimeout){
    	this.serviceName = serviceName;
    	this.operation = operation;
    	this.request = request;
    	this.requestType = requestType;
    	this.responseTimeout = responseTimeout;
    }

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public T getRequest() {
		return request;
	}

	public void setRequest(T request) {
		this.request = request;
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}



	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}



	public long getResponseTimeout() {
		return responseTimeout;
	}

	public void setResponseTimeout(long responseTimeout) {
		this.responseTimeout = responseTimeout;
	}

	public Map<String, Object> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(Map<String, Object> additionalParams) {
		this.additionalParams = additionalParams;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "ServiceRequest [serviceName=" + serviceName + ", requestID="
				+ requestID + ", operation=" + operation + ", requestType="
				+ requestType + ", request=" + request + ", requestUrl=" + requestUrl + ",target=" + target +", responseTimeout="+responseTimeout+ "]";
	}


}
