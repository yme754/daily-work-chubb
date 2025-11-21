package com.webflux.dto;

import java.util.List;

import com.webflux.enums.MealPreference;
import com.webflux.enums.TripType;

import lombok.Data;

@Data
public class BookingRequest {

    private Long flightId;
    private String email;
    private int seatCount;
    private TripType tripType;
    private MealPreference mealPreference;
    private List<PassengerDto> passengers;

    @Data
    public static class PassengerDto {
        private String name;
        private String email;
        private Integer age;
        private String seatNum;
        private String gender;
    }
}
