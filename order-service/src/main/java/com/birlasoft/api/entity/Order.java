package com.birlasoft.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ORDER_TB")

public class Order {

	@Id
	@GeneratedValue
	private int orderId;
	private int cartId;
	private String userName;
	private int total;
	public Order(int orderId, int cartId, String userName, int total) {
		super();
		this.orderId = orderId;
		this.cartId = cartId;
		this.userName = userName;
		this.total = total;
	}
	public Order() {
		super();
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", cartId=" + cartId + ", userName=" + userName + ", total=" + total + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartId;
		result = prime * result + orderId;
		result = prime * result + total;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (cartId != other.cartId)
			return false;
		if (orderId != other.orderId)
			return false;
		if (total != other.total)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	
}
