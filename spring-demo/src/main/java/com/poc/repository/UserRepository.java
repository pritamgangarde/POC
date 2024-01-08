package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}