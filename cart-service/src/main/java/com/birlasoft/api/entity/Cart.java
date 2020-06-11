package com.birlasoft.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="CART_TB")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	@Id
	@GeneratedValue
	private int cartId;
	private int productId;
	private String	 userName;
	

}
