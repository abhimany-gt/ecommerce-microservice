package com.birlasoft.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.birlasoft.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByUsername(String username);

  User findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);

}
