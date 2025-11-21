package com.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.webflux.entity.Passenger;

import reactor.core.publisher.Flux;

public interface PassengerRepository extends ReactiveCrudRepository<Passenger, Long> {
    Flux<Passenger> findByBookingId(Long bookingId);
}
