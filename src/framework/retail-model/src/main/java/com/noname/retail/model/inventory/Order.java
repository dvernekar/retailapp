package com.noname.retail.model.inventory;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.noname.retail.model.usermgmt.Address;
import com.noname.retail.model.usermgmt.User;

@Entity
@Table(name="Orders")
public class Order {

	@Id
	@GeneratedValue
	private Integer id;
	
	private long order_date;
	
	private long required_date;
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL)
	private Collection<OrderItem> order_items;
	
	private long shipped_date;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private User user;

	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getOrder_date() {
		return order_date;
	}

	public void setOrder_date(long order_date) {
		this.order_date = order_date;
	}

	public long getRequired_date() {
		return required_date;
	}

	public void setRequired_date(long required_date) {
		this.required_date = required_date;
	}

	public long getShipped_date() {
		return shipped_date;
	}

	public void setShipped_date(long shipped_date) {
		this.shipped_date = shipped_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
