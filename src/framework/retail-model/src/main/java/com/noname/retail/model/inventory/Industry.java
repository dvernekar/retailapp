package com.noname.retail.model.inventory;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.noname.retail.model.usermgmt.User;

@Entity
@Table(name="industry")
public class Industry {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String desc;
	
	@ManyToMany
	@JoinTable(name="product_industry",
		joinColumns = {@JoinColumn(name="industry_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="product_id", referencedColumnName="id")}
	) 
	private Collection<Product> products;

	@ManyToMany
	@JoinTable(name="user_industry",
		joinColumns = {@JoinColumn(name="industry_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="user_id", referencedColumnName="user_id")}
	)
	private Collection<User> users;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	
	
	
	
}
