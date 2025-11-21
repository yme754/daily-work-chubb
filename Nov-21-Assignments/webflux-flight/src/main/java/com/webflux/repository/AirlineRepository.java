package com.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.webflux.entity.Airline;

public interface AirlineRepository extends ReactiveCrudRepository<Airline, Long> {
	
}

