package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.request.Order;
import com.example.demo.request.Order1;
import com.example.demo.service.OrderService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderService service;
	@GetMapping
	String getOrder() {
		return "hello";
	}
	@PostMapping
		public Order1 saveOrder(@RequestBody @Valid Order1 order) {
//			float total = order.getPrice()* order.getQuantity();
//			order.setPrice(total);
		log.debug("received order: {}", order);
        return service.insertOrder(order);
	}
}
