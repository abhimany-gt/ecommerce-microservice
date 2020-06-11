package com.birlasoft.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.birlasoft.api.commons.Cart;
import com.birlasoft.api.commons.Request;
import com.birlasoft.api.entity.Order;
import com.birlasoft.api.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author abhi
 *
 */
@Slf4j
@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	@Autowired
	private RestTemplate restTemplate;
	public Order createOrder(Request body) {
		String userName=body.getUserName();
		String url="http://localhost:8181/cart/product/"+userName;
		Cart cart=restTemplate.getForObject(url, Cart.class);
		Order order=new Order();
		order.setCartId(cart.getCartId());
		order.setUserName(userName);
		order.setTotal(2345);
		Order createdOrder=repository.save(order);
	int cartId=createdOrder.getCartId();
log.info("before delete method");
	restTemplate.delete("http://localhost:8181/cart/product/{id}", cartId);
	return createdOrder;
	}
}
