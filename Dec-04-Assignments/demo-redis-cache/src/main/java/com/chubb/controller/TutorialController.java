package com.chubb.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chubb.model.Tutorial;
import com.chubb.service.TutorialService;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {
	@Autowired
    private TutorialService service;

    @GetMapping
    public List<Tutorial> getAllTutorials() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createTutorial(@RequestBody Tutorial tutorial) {
    	Tutorial saved = service.save(tutorial);
        return ResponseEntity.created(URI.create("/api/tutorials/" + saved.getId())).body(Map.of("id", saved.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable String id, @RequestBody Tutorial tutorial) {
        return service.update(id, tutorial)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutorial(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
