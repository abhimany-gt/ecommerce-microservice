package com.birlasoft.api.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.birlasoft.api.commons.Cart;
import com.birlasoft.api.commons.Request;
import com.birlasoft.api.controller.Interceptor;
import com.birlasoft.api.entity.Order;
import com.birlasoft.api.repository.OrderRepository;

/**
 * @author abhi
 *
 */

@Service
public class OrderService {

	
	@Autowired
	private OrderRepository repository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;
	
	static Logger log=LoggerFactory.getLogger(OrderService.class);
	
	public Order createOrder(Request body) {
		
			//find baseurl from zuul-service
				List<ServiceInstance> instances = discoveryClient.getInstances("zuul-service");
				String baseUrl = instances.get(0).getUri().toString();
				String cartUrl = baseUrl + "/" + "cart/product/" + Interceptor.getUsername();
	
				//add token in header
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				headers.add("Authorization", "Bearer " + Interceptor.getToken());
				
				
				/*
		 * String userName=body.getUserName(); String
		 * url="http://localhost:7773/cart/product/";
		 */
				
				//call rest template /product/{id}
				ResponseEntity<Cart> response = restTemplate.exchange(cartUrl, HttpMethod.GET,
						new HttpEntity<>("parameters", headers), Cart.class);
				
		Cart cart=response.getBody();
		Order order=new Order();
		order.setCartId(cart.getCartId());
		order.setUserName(Interceptor.getUsername());
		Order createdOrder=repository.save(order);
		int cartId=createdOrder.getCartId();
		log.info("before delete method");
		
		String url=baseUrl+"/cart/product/";
		
	//restTemplate.delete("http://localhost:7773/cart/product/{id}", cartId);
		restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>("parameters",headers), Void.class, cartId); 
		return createdOrder;
	}
}
