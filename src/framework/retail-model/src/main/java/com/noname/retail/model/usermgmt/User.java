package com.noname.retail.model.usermgmt;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.noname.retail.model.inventory.Industry;
import com.noname.retail.model.inventory.Order;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Integer id;

	@Column(name="user_login")
	private String login;

	private String password;
	
	private String first_name;
	
	private String middle_name;
	
	private String last_name;
	
	private String email;
	
	private String phone;
	
	private String company_name;
	
	private String website;
	
	private boolean is_active;
	
	private boolean is_delete;

	@OneToOne
	@JoinTable(name="user_roles",
		joinColumns = {@JoinColumn(name="user_id", referencedColumnName="user_id")},
		inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
	)
	private Role role;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Collection<Order> orders;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Collection<AuditTrail> auditTrails;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	private long creation_date;
	
	private long updation_date;
	
	@ManyToMany
	@JoinTable(name="user_industry",
		joinColumns = {@JoinColumn(name="user_id", referencedColumnName="user_id")},
		inverseJoinColumns = {@JoinColumn(name="industry_id", referencedColumnName="id")}
	)
	private Collection<Industry> industry;
	
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(long creation_date) {
		this.creation_date = creation_date;
	}

	public long getUpdation_date() {
		return updation_date;
	}

	public void setUpdation_date(long updation_date) {
		this.updation_date = updation_date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	public Collection<AuditTrail> getAuditTrails() {
		return auditTrails;
	}

	public void setAuditTrails(Collection<AuditTrail> auditTrails) {
		this.auditTrails = auditTrails;
	}

	public Collection<Industry> getIndustry() {
		return industry;
	}

	public void setIndustry(Collection<Industry> industry) {
		this.industry = industry;
	}

	
}
