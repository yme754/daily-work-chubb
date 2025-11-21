package com.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.webflux.entity.Flight;

import reactor.core.publisher.Flux;

public interface FlightRepository extends ReactiveCrudRepository<Flight, Long> {
    Flux<Flight> findByFromLocAndToLoc(String fromLoc, String toLoc);
}
