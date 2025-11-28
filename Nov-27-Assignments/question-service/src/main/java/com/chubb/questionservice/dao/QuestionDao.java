package com.chubb.questionservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chubb.questionservice.model.Question;

public interface QuestionDao extends JpaRepository<Question, Integer>{
	List<Question> findByCategory(String category);
}
