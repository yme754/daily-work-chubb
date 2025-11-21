package com.webflux.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.webflux.enums.MealPreference;
import com.webflux.enums.TripType;

import lombok.Data;

@Data
@Table("booking")
public class Booking {
    @Id
    private Long id;
    private String pnr;
    private Long flightId;
    private Float totalPrice;
    private LocalDateTime bookingTime;
    private String email;
    @Column("seat_count")
    private int seatCount;
    @Column("trip_type")
    private TripType tripType;
    @Column("meal_preference")
    private MealPreference mealPreference;
}
