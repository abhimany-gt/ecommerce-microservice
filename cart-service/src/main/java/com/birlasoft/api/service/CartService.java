package com.birlasoft.api.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

import com.birlasoft.api.Interceptor;
import com.birlasoft.api.commons.Product;
import com.birlasoft.api.commons.Request;
import com.birlasoft.api.entity.Cart;
import com.birlasoft.api.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository repository;
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private RestTemplate restTemplate;

	static Logger log = LoggerFactory.getLogger(CartService.class);

	public Cart addInCart(Request request) {

		// find baseurl from zuul-service
		List<ServiceInstance> instances = discoveryClient.getInstances("zuul-service");
		String baseUrl = instances.get(0).getUri().toString();
		String producturl = baseUrl + "/" + "products/" + request.getProductId();

		// add token in header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Bearer " + Interceptor.getToken());

		// call rest template /product/{id}
		ResponseEntity<Product> response = restTemplate.exchange(producturl, HttpMethod.GET,
				new HttpEntity<>("parameters", headers), Product.class);

		log.info("Respnse from product/{id}: " + response);
		// create cart object
		Product product = response.getBody();
		Cart cart = new Cart();
		cart.setProductId(product.getId());
		cart.setUserName(Interceptor.getUsername());
		cart.setPrice(product.getPrice());
		return repository.save(cart);

	}

	public void removeFromCart(int cartId) {
		repository.deleteById(cartId);

	}

	public Optional<Cart> getByUserName(String userName) {

		Optional<Cart> response = repository.findByUserName(userName);

		return response;
	}

}
