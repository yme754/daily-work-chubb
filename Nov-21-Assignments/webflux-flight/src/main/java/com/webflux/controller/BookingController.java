package com.webflux.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.dto.BookingRequest;
import com.webflux.entity.Booking;
import com.webflux.entity.Passenger;
import com.webflux.service.BookingService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    @GetMapping("/booking")
    public Flux<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
    @PostMapping("/booking")
    public Mono<Booking> bookFlight(@RequestBody Mono<BookingRequest> req) {
        return bookingService.bookTicket(req);
    }
    @GetMapping("/booking/{pnr}")
    public Mono<Booking> getBooking(@PathVariable String pnr) {
        return bookingService.getByPnr(pnr);
    }
    @DeleteMapping("/booking/cancel/{pnr}")
    public Mono<Booking> cancelBooking(@PathVariable String pnr) {
        return bookingService.cancelByPnr(pnr);
    }
    @GetMapping("/booking/{id}/passengers")
    public Flux<Passenger> getPassengers(@PathVariable Long id) {
        return bookingService.getPassengersForBooking(id);
    }
}