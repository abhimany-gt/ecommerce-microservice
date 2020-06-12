package com.birlasoft.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.birlasoft.api.commons.Product;
import com.birlasoft.api.commons.Request;
import com.birlasoft.api.entity.Cart;
import com.birlasoft.api.service.CartService;



@RestController
//@RequestMapping("/cart")

public class CartController {

	@Autowired
	private CartService service;

	static Logger log=LoggerFactory.getLogger(CartController.class);
	
	@PostMapping("/product")
	public ResponseEntity addProductInCart(@RequestBody Request request) {
		
		log.info("addProductInCart  method is running");
		log.info("Request "+request);

		
		try {
			Cart saveCart=service.addInCart(request);
			if(request.getProductId()!=0 && saveCart != null) {
				return new ResponseEntity<Cart>(saveCart,HttpStatus.OK);	 
			}
			else
				return new ResponseEntity<String>("Product not Added in Cart Successsfully",HttpStatus.OK);
		
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}

		
	}

	@DeleteMapping("/product/{id}")
	public void removeProductById(@PathVariable("id") int cartId) {
		log.info("removeProductById method");
		service.removeFromCart(cartId);
	}

	@GetMapping("/product/{userName}")
	public ResponseEntity getProductByUserName(@PathVariable("userName") String userName) {

		log.info("getProductByUserName method with User Name: "+ userName);
		return new ResponseEntity<>(service.getByUserName(userName), HttpStatus.OK);
	}

}
