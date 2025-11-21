package com.webflux.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.webflux.entity.Booking;
import com.webflux.entity.Passenger;

import lombok.Data;

@Data
public class TicketResponse {
    private String pnr;
    private Long bookingId;
    private Long flightId;
    private float totalPrice;
    private LocalDateTime bookingTime;
    private List<Passenger> passengers;
    public TicketResponse(Booking b, List<Passenger> passengers) {
        this.pnr = b.getPnr();
        this.bookingId = b.getId();
        this.flightId = b.getFlightId();
        this.totalPrice = b.getTotalPrice();
        this.bookingTime = b.getBookingTime();
        this.passengers = passengers;
    }
}
