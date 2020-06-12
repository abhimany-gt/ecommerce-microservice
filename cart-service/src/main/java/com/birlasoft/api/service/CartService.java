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

	// static String username=Interceptor.username;
	public Cart addInCart(Request request) {

		// call rest api of getproduct by id . here using test methodg

		List<ServiceInstance> instances = discoveryClient.getInstances("zuul-service");
		String baseUrl = instances.get(0).getUri().toString();
		String producturl = baseUrl + "/" + "products/" + request.getProductId();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Bearer " + Interceptor.token);
		ResponseEntity<Product> product = restTemplate.exchange(producturl, HttpMethod.GET,
				new HttpEntity<>("parameters", headers), Product.class);
		log.info("product" + product);
		Product prpd = product.getBody();

		// Product product =getProductById(request.getProductId());
		Cart cart = new Cart();
		cart.setProductId(prpd.getId());
		cart.setUserName(Interceptor.username);
		return repository.save(cart);
//		return new Response(user,cart,product);

	}

	public void removeFromCart(int cartId) {
		repository.deleteById(cartId);

	}

	public Optional<Cart> getByUserName(String userName) {
		return repository.findByUserName(userName);
	}

}
