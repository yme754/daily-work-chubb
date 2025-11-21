package com.webflux.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("passenger")
public class Passenger {
    @Id
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String seatNum;
    private Long bookingId;
    @Column("gender")
    private String gender;
}
