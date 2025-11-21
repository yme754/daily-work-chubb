package com.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.entity.Flight;
import com.webflux.service.FlightService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    @GetMapping("/flights")
    public Flux<Flight> getAll() {
        return flightService.getAllFlights();
    }
    @GetMapping("/flights/{id}")
    public Mono<Flight> getById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }
    @GetMapping("/flights/search")
    public Flux<Flight> search(@RequestParam String from, @RequestParam String to) {
        return flightService.searchFlights(from, to);
    }
    @PostMapping("/admin/flights")
    public Mono<Flight> createFlight(@RequestBody Mono<Flight> flightMono) {
        return flightMono.flatMap(flightService::createFlight);
    }
}
