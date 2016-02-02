package com.noname.retail.appserver.token;

import java.util.Date;

public class TokenInfo {
	private String refreshToken;
	private String clientId ; 
	private Date expiration;
	private String statusMsg; 
	/**
	 * paramMap.put("username", user.getLogin());
		paramMap.put("password", user.getConfPassword());
		paramMap.put("grant_type", "password");
		paramMap.put("client_id", clientId);
		paramMap.put("client_secret", clientSecret);
	 */
	public static final String CLIENT_ID = "client_id";
	public static final String CLIENT_SECRET = "client_secret";
	public static final String GRANT_TYPE = "grant_type";
	public static final String USER_NAME = "username";
	public static final String PASSWORD = "password";
	
	public TokenInfo(){
		super();
	}
	
	public TokenInfo(String refreshToken, String clientId, Date expiration) {
		super();
		this.refreshToken = refreshToken;
		this.clientId = clientId;
		this.expiration = expiration;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	
}
