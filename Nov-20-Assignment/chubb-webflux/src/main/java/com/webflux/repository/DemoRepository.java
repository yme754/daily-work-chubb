package com.webflux.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.webflux.model.Demo;

import reactor.core.publisher.Flux;

public interface DemoRepository extends R2dbcRepository<Demo, Integer>{
	 Flux<Demo> findByPublished(Boolean published);
	 Flux<Demo> findByTitleContaining(String title);
}
