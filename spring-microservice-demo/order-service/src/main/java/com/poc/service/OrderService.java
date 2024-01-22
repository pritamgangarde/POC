package com.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poc.dto.Payment;
import com.poc.dto.TransactionRequest;
import com.poc.dto.TransactionResponse;
import com.poc.entity.Order;
import com.poc.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTemplate;

	public TransactionResponse saveOrder(TransactionRequest transactionRequest) {
		String message = "";
		Order order = transactionRequest.getOrder();
		order = orderRepository.save(order);
		Payment payment = transactionRequest.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice() * order.getQty());
//		Payment paymentResponse = restTemplate.postForObject("http://localhost:9193/payment/doPayment", payment,
//				Payment.class);
		Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment,
				Payment.class);
		message = paymentResponse.getPaymentStatus().equals("SUCCESS")
				? "payment processing successfull and order is placed"
				: "there issue in payment please contact administrator";

		return new TransactionResponse(order, paymentResponse.getTransactionId(), payment.getAmount(), message);
	}
}
