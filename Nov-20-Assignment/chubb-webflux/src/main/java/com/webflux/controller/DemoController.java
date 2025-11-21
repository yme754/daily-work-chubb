package com.webflux.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.model.Demo;
import com.webflux.service.DemoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
	private final DemoService service;
    public DemoController(DemoService service) {
        this.service = service;
    }
    @GetMapping
    public Flux<Demo>getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Demo>>getById(@PathVariable Integer id) {
        return service.getById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Mono<ResponseEntity<Integer>> create(@RequestBody Demo demo) {
        return service.create(demo).map(saved -> ResponseEntity.status(201).body(saved.getId()));
    }
    @DeleteMapping("/{id}")
    public Mono<Void>delete(@PathVariable Integer id) {
        return service.delete(id);
    }
    @GetMapping("/published")
    public Flux<Demo> getPublished() {
        return service.findPublished();
    }
    @GetMapping("/title")
    public Flux<Demo> getByTitle(@RequestParam String title) {
        return service.findByTitleContaining(title);
    }
}
