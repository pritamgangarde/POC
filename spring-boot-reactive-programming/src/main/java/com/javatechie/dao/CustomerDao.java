package com.javatechie.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.javatechie.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerDao {

	public static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<Customer> getCustomers() {
		return IntStream.rangeClosed(1, 10).peek(CustomerDao::sleepExecution)
				.peek(i -> System.out.println("Processing Count->" + i)).mapToObj(i -> new Customer(i, "Customer " + i))
				.toList();
	}

	public Flux<Customer> getCustomersStream() {
		return Flux.range(1, 10).doOnNext(i -> System.out.println("Processing Count flux flow ->" + i))
				.delayElements(Duration.ofSeconds(1)).map(i -> new Customer(i, "Customer " + i));
	}

	public Flux<Customer> getCustomersList() {
		return Flux.range(1, 10).doOnNext(i -> System.out.println("Processing Count flux flow ->" + i))
				.map(i -> new Customer(i, "Customer " + i));
	}
	
	public Mono<Customer> findCustomerById(Integer customerId){
		return getCustomersList().filter(customer->customer.getId()==customerId).next();
	}
}
