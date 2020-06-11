package com.birlasoft.api.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	private int cartId;
	private int productId;
	private String userName;
}
