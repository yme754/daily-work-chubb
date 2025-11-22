package com.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.model.Student;
import com.mongodb.repository.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MainController {
	@Autowired
	StudentRepository studentRepo;
	@GetMapping("/fetchStudent")
	public Flux<Student> fetchStudent() {
		return studentRepo.findAll();
	}
	
	@PostMapping("/addStudent")
	public Mono<Integer> addStudent(@RequestBody Student student) {
		return studentRepo.save(student).map(Student::getRno);
	}
	@GetMapping("getStudent/{id}")
	public Mono<Student> getStudent(@PathVariable Integer id) {
		return studentRepo.findById(id);
	}
	@PutMapping("/updateStudent")
    public Mono<String> updateStudent(@RequestBody Student student) {
        return studentRepo.findById(student.getRno())
                .flatMap(existing -> {
                    existing.setName(student.getName());
                    existing.setAddress(student.getAddress());
                    return studentRepo.save(existing).then(Mono.just("updated"));
                })
                .defaultIfEmpty("roll no doesn't exist");
    }
	@DeleteMapping("/deleteStudent/{id}")
	public Mono<String> deleteStudent(@PathVariable Integer id) {
		return studentRepo.findById(id)
				.flatMap(student -> 
					studentRepo.deleteById(id).then(Mono.just("deleted rno: " + id))
					)
				.defaultIfEmpty("not found");
	}
}
