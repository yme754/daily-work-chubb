package com.webflux.service.implementation;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.webflux.service.BookingService;
import com.webflux.entity.Booking;
import com.webflux.entity.Passenger;
import com.webflux.repository.BookingRepository;
import com.webflux.repository.FlightRepository;
import com.webflux.repository.PassengerRepository;
import com.webflux.dto.BookingRequest;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookingSImplementation implements BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;

    @Override
    public Mono<Booking> bookTicket(Mono<BookingRequest> reqMono) {
        return reqMono.flatMap(req -> 
            flightRepository.findById(req.getFlightId())
                .switchIfEmpty(Mono.error(new RuntimeException("Flight not found")))
                .flatMap(flight -> {
                    if (flight.getSeatsAvailable() == null || flight.getSeatsAvailable() <= 0) return Mono.error(new RuntimeException("No seats available"));
                    Booking b = new Booking();
                    b.setPnr("PNR" + System.currentTimeMillis());
                    b.setFlightId(flight.getId());
                    b.setTotalPrice(flight.getPrice() * req.getPassengers().size());
                    b.setBookingTime(LocalDateTime.now());
                    b.setEmail(req.getEmail());
                    b.setSeatCount(req.getSeatCount());
                    b.setTripType(req.getTripType());
                    b.setMealPreference(req.getMealPreference());
                    return bookingRepository.save(b)
                        .flatMap(saved -> {
                            flight.setSeatsAvailable(flight.getSeatsAvailable() - req.getPassengers().size());
                            return flightRepository.save(flight).thenMany(Flux.fromIterable(req.getPassengers())
                                    .map(p -> {
                                        Passenger pass = new Passenger();
                                        pass.setName(p.getName());
                                        pass.setEmail(p.getEmail());
                                        pass.setAge(p.getAge());
                                        pass.setSeatNum(p.getSeatNum());
                                        pass.setBookingId(saved.getId());
                                        pass.setEmail(p.getEmail());
                                        pass.setGender(p.getGender());
                                        return pass;
                                    })
                                )
                                .flatMap(passengerRepository::save)
                                .collectList()
                                .then(Mono.just(saved));
                        });
                })
        );
    }
    @Override
    public Mono<Booking> cancelByPnr(String pnr) {
        return bookingRepository.findByPnr(pnr)
            .switchIfEmpty(Mono.error(new RuntimeException("Booking not found")))
            .flatMap(b -> bookingRepository.delete(b).thenReturn(b));
    }
    @Override
    public Mono<Booking> getByPnr(String pnr) {
        return bookingRepository.findByPnr(pnr);
    }
    @Override
    public Flux<Passenger> getPassengersForBooking(Long bookingId) {
        return passengerRepository.findByBookingId(bookingId);
    }
	public FlightRepository getFlightRepository() {
		return flightRepository;
	}
	@Override
	public Flux<Booking> getAllBookings() {
	    return bookingRepository.findAll();
	}
}
