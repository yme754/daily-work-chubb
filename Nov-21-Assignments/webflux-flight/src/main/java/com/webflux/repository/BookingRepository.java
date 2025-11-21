package com.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.webflux.entity.Booking;

import reactor.core.publisher.Mono;

public interface BookingRepository extends ReactiveCrudRepository<Booking, Long> {
    Mono<Booking> findByPnr(String pnr);
    
}
