package com.javatechie.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.javatechie.handler.CustomerHandler;
import com.javatechie.handler.CustomerStreamHandler;

@Configuration
public class RouterConfig {

	@Autowired
	private CustomerHandler customerHandler;

	@Autowired
	private CustomerStreamHandler customerStreamHandler;

	@Bean
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route().GET("/router/customers", customerHandler::loadCustomers)
				.GET("/router/customers/stream",customerStreamHandler::loadAllCustomers)
				.GET("/router/customers/{input}",customerHandler::findCustomerById).
				POST("/router/customers/save",customerHandler::saveCustomer).build();
	}
}
