package com.javatechie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.dao.CustomerDao;
import com.javatechie.dto.Customer;

import reactor.core.publisher.Flux;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public List<Customer> getCustomers() {
		long start = System.currentTimeMillis();
		List<Customer> listCustomer = customerDao.getCustomers();
		long end = System.currentTimeMillis();
		System.out.println("Total execution time=" + (end - start));
		return listCustomer;
	}

	public Flux<Customer> getCustomersStream() {
		long start = System.currentTimeMillis();
		Flux<Customer> listCustomer = customerDao.getCustomersStream();
		long end = System.currentTimeMillis();
		System.out.println("Total execution flux flow time=" + (end - start));
		return listCustomer;
	}
}
