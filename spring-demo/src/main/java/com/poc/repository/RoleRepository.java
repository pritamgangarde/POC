package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}