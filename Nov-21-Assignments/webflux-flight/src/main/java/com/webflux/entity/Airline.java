package com.webflux.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("airline")
public class Airline {
    @Id
    private Float id;
    private String name;
    @Column("logo_url")
    private String logoUrl;
}
