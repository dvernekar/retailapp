package com.noname.retail.model.usermgmt;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Address")
public class Address {
	
	@Id
	@GeneratedValue
	private Integer address_id;
	
	private String line1;
	
	private String line2;
	
	private String line3;
	
	@ManyToOne
	@JoinColumn(name="city_code")
	private City city;
	
	private Integer zip_code;

	@OneToOne(mappedBy="address")
	private User user;
	
	public Integer getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getLine3() {
		return line3;
	}

	public void setLine3(String line3) {
		this.line3 = line3;
	}

	public Integer getZip_code() {
		return zip_code;
	}

	public void setZip_code(Integer zip_code) {
		this.zip_code = zip_code;
	}
	
	

}
