package com.poc.repository;

import org.springframework.data.repository.CrudRepository;

import com.poc.entity.Product;


public interface ProductRepositroy extends CrudRepository<Product, Integer> {

}
