package com.webflux.service;

import com.webflux.dto.BookingRequest;
import com.webflux.entity.Booking;
import com.webflux.entity.Passenger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingService {
    Mono<Booking> bookTicket(Mono<BookingRequest> reqMono);
    Mono<Booking> cancelByPnr(String pnr);
    Mono<Booking> getByPnr(String pnr);
    Flux<Passenger> getPassengersForBooking(Long bookingId);
    Flux<Booking> getAllBookings();
}
