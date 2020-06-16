package com.birlasoft.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.birlasoft.api.commons.Cart;
import com.birlasoft.api.commons.Request;
import com.birlasoft.api.entity.Order;
import com.birlasoft.api.service.OrderService;

/**
 * @author Abhi
 *
 */
@RestController
//@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService service;

	private static Logger log = LoggerFactory.getLogger(OrderController.class);

	@PostMapping("/product")
	public ResponseEntity createOrder(@RequestBody Request body) {

		log.info("createOrder  method is running");
		log.info("Request " + body);

		try {
			Order order = service.createOrder(body);
			if (body.getProductId() != 0 && order != null) {
				return new ResponseEntity<Order>(order, HttpStatus.OK);
			} else
				return new ResponseEntity<String>("Product not Added in Cart Successsfully", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
