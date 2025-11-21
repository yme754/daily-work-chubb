package com.webflux.service;

import org.springframework.stereotype.Service;

import com.webflux.model.Demo;
import com.webflux.repository.DemoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DemoService {
	private final DemoRepository repo;
	public DemoService(DemoRepository repo) {
		this.repo = repo;
	}
	public Flux<Demo>getAll() {
        return repo.findAll();
    }
    public Mono<Demo>getById(Integer id) {
        return repo.findById(id);
    }
    public Mono<Demo> create(Demo obj) {
        return repo.save(obj);
    }
    public Mono<Void>delete(Integer id) {
        return repo.deleteById(id);
    }
    public Flux<Demo>findPublished() {
        return repo.findByPublished(true);
    }
    public Flux<Demo> findByTitleContaining(String title) {
        return repo.findByTitleContaining(title);
    }
}
