package com.birlasoft.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.birlasoft.api.commons.Request;
import com.birlasoft.api.service.OrderService;

/**
 * @author Abhi
 *
 */
@RestController
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	
	@PostMapping("/product")
	public ResponseEntity createOrder(@RequestBody Request body) {
		// rest call cart- service method getByUserNmae
		/*
		 * String url="http://localhost:8181/cart/product/john";
		 * 
		 * //restTemplate.exchange(url,HttpMethod.POST,); restTemplate.getForEntity(url,
		 * Object, request.getUserName());
		 */
		
		return new ResponseEntity<>(service.createOrder(body), HttpStatus.OK);
	}

}
