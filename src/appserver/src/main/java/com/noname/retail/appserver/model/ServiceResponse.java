package com.noname.retail.appserver.model;

import java.util.HashMap;
import java.util.Map;

import com.noname.retail.appserver.controller.NGServerUtils;
import com.noname.retail.util.OVVersionUtil;

public class ServiceResponse<T> {

	private String serviceName;

	boolean isFinal = false;

	private T data;

	private int requestType;

	private String requestUrl;

	private String correlationID;

	private String errorMessage;

	/** setting default status to success. This helps in case if some module operation fails to set status**/
	private String status = NGServerUtils.Success;

	private int operation;

	private String version = OVVersionUtil.ORIGINAL_VERSION;

	private Map<String, Object> additionalParams = new HashMap<String, Object>();

	public ServiceResponse() {

	}

	public ServiceResponse(T data) {
		this.data = data;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setAdditionalParams(Map<String, Object> additionalParams) {
		this.additionalParams = additionalParams;
	}

	public Map<String, Object> getAdditionalParams() {
		return additionalParams;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getCorrelationID() {
		return correlationID;
	}

	public void setCorrelationID(String correlationID) {
		this.correlationID = correlationID;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}



}
