package com.dbr.retail.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public abstract class BaseObject {


	private int version = 0;
	
	@JsonIgnore
	@JsonProperty(value = "objectDisplayName")
	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	@JsonIgnore
	@JsonProperty(value = "objectDisplayName")
	public String getObjectDisplayName() {
		return this.getClass().getName();
	}

}
