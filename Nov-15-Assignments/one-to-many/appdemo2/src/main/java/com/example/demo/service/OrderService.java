package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.OrderRepository;
import com.example.demo.request.Order1;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	public Order1 insertOrder(Order1 order) {
        Order1 saved = orderRepository.save(order);
        log.debug("saved order: {}", saved);
        return saved;
    }
}