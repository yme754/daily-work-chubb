package com.webflux.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("flight")
public class Flight {
    @Id
    private Long id;
    private Long airlineId;
    private String flightNumber;
    private String fromLoc;
    private String toLoc;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Float price;
    private Integer seatsAvailable;
}
