package com.webflux.service;

import com.webflux.entity.Flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightService {
    Flux<Flight> getAllFlights();
    Mono<Flight> getFlightById(Long id);
    Flux<Flight> searchFlights(String from, String to);
    Mono<Flight> createFlight(Flight flight);
}
