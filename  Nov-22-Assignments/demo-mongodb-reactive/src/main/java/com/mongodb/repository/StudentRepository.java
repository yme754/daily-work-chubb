package com.mongodb.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mongodb.model.Student;

public interface StudentRepository extends ReactiveMongoRepository<Student, Integer>{

}
