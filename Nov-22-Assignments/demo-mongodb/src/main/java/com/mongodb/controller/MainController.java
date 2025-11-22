package com.mongodb.controller;

import java.util.List;

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

@RestController
public class MainController {
	@Autowired
	StudentRepository studentRepo;
	@GetMapping("/fetchStudent")
	public List<Student> fetchStudent() {
		return studentRepo.findAll();
	}
	@PostMapping("/addStudent")
	public Integer addStudent(@RequestBody Student student) {
		studentRepo.save(student);
		return student.getRno();
	}
	@GetMapping("/getStudent/{id}")
	public Student getStudent(@PathVariable Integer id) {
		return studentRepo.findById(id).orElse(null);
	}
	@PutMapping("updateStudent")
	public String updateStudent(@RequestBody Student student) {
		Student data = studentRepo.findById(student.getRno()).orElse(null);
		System.out.println(data);
		if(data != null) {
			data.setName(student.getName());
			data.setAddress(student.getAddress());
			studentRepo.save(data);
			return "updated";
		}
		return "roll no doesn't exist";
	}
	@DeleteMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable Integer id) {
		studentRepo.deleteById(id);
		return "deleted rno: " + id;
	}
}
