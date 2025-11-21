package com.webflux.service.implementation;

import org.springframework.stereotype.Service;

import com.webflux.entity.Flight;
import com.webflux.repository.FlightRepository;
import com.webflux.service.FlightService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FlightSImplementation implements FlightService {

    private final FlightRepository flightRepository;
    @Override
    public Flux<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    @Override
    public Mono<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }
    @Override
    public Flux<Flight> searchFlights(String from, String to) {
        return flightRepository.findByFromLocAndToLoc(from, to);
    }
    @Override
    public Mono<Flight> createFlight(Flight flight) {
        return flightRepository.save(flight);
    }
}
