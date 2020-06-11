package com.birlasoft.api;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.birlasoft.api.entity.Cart;
import com.birlasoft.api.repository.CartRepository;

@SpringBootApplication
//@EnableEurekaClient
public class CartServiceApplication {

	@Autowired
	private CartRepository repo;
	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@PostConstruct
	public void init() {
		List<Cart> list=Arrays.asList(new Cart(1, 1001, "john"),
				new Cart(2, 1002, "james"),
				new Cart(3, 1003, "Janice"),
				new Cart(4, 1004, "Jenny")
				);
		repo.saveAll(list);
		
		
	}
}
