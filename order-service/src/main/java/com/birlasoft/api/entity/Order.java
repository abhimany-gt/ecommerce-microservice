package com.birlasoft.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="ORDER_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue
	private int orderId;
	private int cartId;
	private String userName;
	private int total;
	
}
