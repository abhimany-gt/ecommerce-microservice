package com.birlasoft.api.commons;

import com.birlasoft.api.entity.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	private User user;
	private Cart cart;
	private Product product;
}
