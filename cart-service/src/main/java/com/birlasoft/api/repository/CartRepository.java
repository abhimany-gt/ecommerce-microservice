package com.birlasoft.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.birlasoft.api.commons.Product;
import com.birlasoft.api.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	public Optional<Cart> findByUserName(String userName);

}
