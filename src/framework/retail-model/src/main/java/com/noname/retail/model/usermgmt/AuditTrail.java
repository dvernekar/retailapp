package com.noname.retail.model.usermgmt;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AuditTrail")
public class AuditTrail {

	@Id
	@GeneratedValue
	private Integer id;
	
	//private Integer client_type_id;
	
	private long creation_time;
	
	private boolean is_delete;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(long creation_time) {
		this.creation_time = creation_time;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
