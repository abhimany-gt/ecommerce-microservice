package com.birlasoft.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.birlasoft.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>  {

}
