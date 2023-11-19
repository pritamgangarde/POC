package com.javatechie.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.javatechie.dao.CustomerDao;
import com.javatechie.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

	@Autowired
	private CustomerDao customerDao;
	
	public Mono<ServerResponse> loadAllCustomers(ServerRequest request){
		Flux<Customer> fluxCustomer=customerDao.getCustomersStream();
		return ServerResponse.ok().body(fluxCustomer,Customer.class);
	}
}
