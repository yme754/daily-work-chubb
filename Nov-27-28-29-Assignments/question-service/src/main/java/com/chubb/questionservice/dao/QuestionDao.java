package com.chubb.questionservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chubb.questionservice.model.Question;

public interface QuestionDao extends JpaRepository<Question, Integer>{
	@Query("SELECT q.id FROM Question q WHERE q.category = :category ORDER BY RAND()")
	List<Integer> findRandomQuestionsByCategory(@Param("category") String category);
	List<Question> findByCategory(String category);
}
