package com.chubb.questionservice.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chubb.questionservice.model.Question;

public interface QuestionDao extends JpaRepository<Question, Integer>{
	@Query(value = "SELECT * FROM question WHERE category = ?1 ORDER BY RAND()", 
	 	       nativeQuery = true)
	 	List<Integer> findRandomQuestionsByCategory(String category, Pageable pageable);
	List<Question> findByCategory(String category);
}
