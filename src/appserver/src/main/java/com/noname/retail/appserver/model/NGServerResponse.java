/*
    $Id:
    Copyright (c) 1997-2013 Alcatel-Lucent. All Rights Reserved.
    No part of this file may be reproduced, stored in a retrieval system,
    or transmitted in any form or by any means, electronic, mechanical,
    photocopying, or otherwise without the prior permission of Alcatel-Lucent.
*/
package com.noname.retail.appserver.model;

public class NGServerResponse {

	private String status;
	private int statusCode;
	private String type;
	private Object response;

	public NGServerResponse(){

	}

	public NGServerResponse(String status, Object response){
		this.status = status;
		this.response = response;
	}

	public NGServerResponse(String status,String objectType, Object response){
		this.status = status;
		this.type = objectType;
		this.response = response;
	}

	public NGServerResponse(String status,int statusCode,String objectType, Object response){
		this.status = status;
		this.statusCode = statusCode;
		this.type = objectType;
		this.response = response;
	}


	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setResponse(Object response){
		this.response = response;
	}

	public Object getResponse(){
		return response;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


}
