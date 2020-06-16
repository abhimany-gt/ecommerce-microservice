package com.birlasoft.api.controller;

import java.util.Optional;

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

	static Logger log = LoggerFactory.getLogger(CartController.class);

	@PostMapping("/product")
	public ResponseEntity addProductInCart(@RequestBody Request request) {

		log.info("addProductInCart  method is running");
		log.info("Request " + request);

		try {
			Cart saveCart = service.addInCart(request);
			if (request.getProductId() != 0 && saveCart != null) {
				return new ResponseEntity<Cart>(saveCart, HttpStatus.CREATED);
			} else
				return new ResponseEntity<String>("Product not Added in Cart Successsfully", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity removeProductById(@PathVariable("id") int cartId) {
		log.info("removeProductById method");
		try {
			if (cartId != 0) {
				service.removeFromCart(cartId);
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else
				return new ResponseEntity<String>("Product not Added in Cart Successsfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/product/{userName}")
	public ResponseEntity getProductByUserName(@PathVariable("userName") String userName) {

		log.info("getProductByUserName method with User Name: " + userName);
		try {
			Optional<Cart> response = service.getByUserName(userName);
			if (userName != null && response != null) {
				return new ResponseEntity<Optional<Cart>>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Cart is empty or user is not registered", HttpStatus.OK);
			}
		}

		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
