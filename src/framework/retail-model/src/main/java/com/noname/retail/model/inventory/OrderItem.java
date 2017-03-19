package com.noname.retail.model.inventory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_line_item")
public class OrderItem {

	@Id
	@GeneratedValue
	private Integer item_id;
	@ManyToOne
	@JoinColumn(name="order_id", nullable=false)
	private Order order;
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	private int quantity;
	private long creation_date;
	private long updation_date;
	private int status_id;
	private float itemPrice;
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProducts(Product product) {
		this.product = product;
	}
	
	
}
