package com.poc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poc.entity.Product;
import com.poc.repository.ProductRepositroy;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private ProductRepositroy productRepositroy;

	@Override
	public List<Product> getAllProduct() {
		return (List<Product>) productRepositroy.findAll();
	}

}
