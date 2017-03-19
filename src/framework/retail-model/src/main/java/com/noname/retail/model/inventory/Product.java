package com.noname.retail.model.inventory;


import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Product")
public class Product {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToMany
	@JoinTable(name="product_industry",
		joinColumns = {@JoinColumn(name="product_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="industry_id", referencedColumnName="id")}
	)
	private Collection<Industry> industry;
	
	@OneToMany(mappedBy="product")
	private Collection<OrderItem> oderItems;
	
	private String description;
	
	private Double price;
	
	private String name;
	
	private boolean isActive;
	
	private long create_time;
	
	private long update_time;
	
	private boolean discontinued;
	
	private Integer units_in_stock;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Collection<Industry> getIndustry() {
		return industry;
	}

	public void setIndustry(Collection<Industry> industry) {
		this.industry = industry;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public long getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(long update_time) {
		this.update_time = update_time;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	public Integer getUnits_in_stock() {
		return units_in_stock;
	}

	public void setUnits_in_stock(Integer units_in_stock) {
		this.units_in_stock = units_in_stock;
	}
	
	
}
