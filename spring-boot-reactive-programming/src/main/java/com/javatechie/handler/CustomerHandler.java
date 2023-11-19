package com.javatechie.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.javatechie.dao.CustomerDao;
import com.javatechie.dto.Customer;

import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {
	@Autowired
	private CustomerDao customerDao;
	
	public Mono<ServerResponse> loadCustomers(ServerRequest request){
		return ServerResponse.ok().body(customerDao.getCustomersList(),Customer.class);
	}
	
	public Mono<ServerResponse> findCustomerById(ServerRequest request){
		Integer customerId=Integer.valueOf(request.pathVariable("input"));
		return ServerResponse.ok().body(customerDao.findCustomerById(customerId),Customer.class);
	}
	
	public Mono<ServerResponse> saveCustomer(ServerRequest request){
		Mono<Customer> monoCustomer=request.bodyToMono(Customer.class);
		Mono<String> saveResponse=monoCustomer.map(dto->dto.getId()+":"+dto.getName());
		return ServerResponse.ok().body(saveResponse,String.class);
	}
}
