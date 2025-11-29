package com.chubb.quizservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chubb.quizservice.dao.QuizDao;
import com.chubb.quizservice.feign.QuizInterface;
import com.chubb.quizservice.model.Quiz;
import com.chubb.quizservice.model.Response;

public class QuizServiceTest {
	@InjectMocks
    QuizService quizService;

    @Mock
    QuizDao quizDao;

    @Mock
    QuizInterface quizInterface;

    @BeforeEach
    void init() { 
    	MockitoAnnotations.openMocks(this); 
    }

    @Test
    void testCreateQuiz() {
        when(quizInterface.getQuestionsForQuiz("Java", 5)).thenReturn(ResponseEntity.ok(List.of(10,11,12,13,14)));
        var response = quizService.createQuiz("Java",5,"Java Quiz");
        verify(quizDao,times(1)).save(any());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals("Success",response.getBody());
    }

    @Test
    void testGetQuizQuestions() {
        Quiz quiz = new Quiz();
        quiz.setQuestionIds(List.of(1,2));
        when(quizDao.findById(1)).thenReturn(Optional.of(quiz));
        when(quizInterface.getQuestionsFromId(List.of(1,2))).thenReturn(ResponseEntity.ok(List.of()));
        var response = quizService.getQuizQuestions(1);
        verify(quizInterface,times(1)).getQuestionsFromId(List.of(1,2));
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void testCalculateScore() {
        when(quizInterface.getScore(any())).thenReturn(ResponseEntity.ok(4));
        var response = quizService.calculateResult(1,List.of(new Response()));
        assertEquals(4,response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
