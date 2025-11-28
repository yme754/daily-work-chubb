package com.chubb.quizservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chubb.quizservice.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz,Integer>{
	
}
