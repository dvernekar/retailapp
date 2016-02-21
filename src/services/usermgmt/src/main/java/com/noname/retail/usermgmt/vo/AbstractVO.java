package com.noname.retail.usermgmt.vo;


public abstract class AbstractVO {
	
	String      uniqueRequestId;
	String 		sessionKey;
	public String getUniqueRequestId() {
		return uniqueRequestId;
	}
	public void setUniqueRequestId(String uniqueRequestId) {
		this.uniqueRequestId = uniqueRequestId;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	@Override
	public String toString() {
		return "AbstractVO [uniqueRequestId=" + uniqueRequestId
				+ ", sessionKey=" + sessionKey + "]";
	}
	
	
	
}
