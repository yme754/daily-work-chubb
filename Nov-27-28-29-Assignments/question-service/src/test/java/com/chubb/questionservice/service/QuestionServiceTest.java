package com.chubb.questionservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import com.chubb.questionservice.dao.QuestionDao;
import com.chubb.questionservice.model.Question;
import com.chubb.questionservice.model.QuestionWrapper;
import com.chubb.questionservice.model.Response;

public class QuestionServiceTest {
	@Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionService questionService;

    public QuestionServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllQuestions() {
        List<Question> mockList = Arrays.asList(new Question(), new Question());
        when(questionDao.findAll()).thenReturn(mockList);
        var response = questionService.getAllQuestions();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetQuestionsByCategory() {
        List<Question> mockList = Arrays.asList(new Question());
        when(questionDao.findByCategory("Java")).thenReturn(mockList);
        var response = questionService.getQuestionsByCategory("Java");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testAddQuestion() {
        Question q = new Question();
        var response = questionService.addQuestion(q);
        verify(questionDao, times(1)).save(q);
        assertEquals("success", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetQuestionsFromId() {
        Question q = new Question();
        q.setId(1); q.setOp1("A"); q.setOp2("B"); q.setOp3("C"); q.setOp4("D");
        when(questionDao.findById(1)).thenReturn(Optional.of(q));
        var result = questionService.getQuestionsFromId(List.of(1));
        assertEquals(1, result.getBody().size());
        assertTrue(result.getBody().get(0) instanceof QuestionWrapper);
    }

    @Test
    void testGetScore() {
        Question q = new Question(); q.setRightAnswer("A");
        when(questionDao.findById(1)).thenReturn(Optional.of(q));
        Response r = new Response(); r.setId(1); r.setResponse("A");
        var result = questionService.getScore(List.of(r));
        assertEquals(1, result.getBody());
    }

    @Test
    void testGetQuestionsForQuiz() {
        when(questionDao.findRandomQuestionsByCategory("Java", PageRequest.of(0,10))).thenReturn(List.of(1,2,3));
        var response = questionService.getQuestionsForQuiz("Java", PageRequest.of(0,10));
        assertEquals(3, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
