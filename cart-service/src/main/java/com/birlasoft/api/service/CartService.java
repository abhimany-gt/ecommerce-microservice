package com.birlasoft.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	
	static String username;
	public Cart addInCart(Request	 request) {
	
		//call rest api of getproduct by id . here using test methodg
		
		List<ServiceInstance> instances=discoveryClient.getInstances("product-service");
		String baseUrl=instances.get(0).getUri().toString();
		String producturl=baseUrl+"/{id}" ;
		Product product=restTemplate.getForObject(producturl, Product.class, request.getProductId());
		
		
		
		//Product product =getProductById(request.getProductId());
		Cart cart=new Cart();
		cart.setProductId(product.getProductId());
		cart.setUserName(username);
		return repository.save(cart);
//		return new Response(user,cart,product);
		
		
	}
	
	private static Product getProductById(int productId) {
		// TODO Auto-generated method stub
		Product prod=new Product(productId, "nivea", 134);
		return prod;
	}

	public void removeFromCart(int cartId) {
		repository.deleteById(cartId);
		
	}

	public Optional<Cart> getByUserName(String userName) {
		return repository.findByUserName(userName);
	}

	public static void getUsername(String username) {
		// TODO Auto-generated method stub
		CartService.username=username;
		
	}
}
