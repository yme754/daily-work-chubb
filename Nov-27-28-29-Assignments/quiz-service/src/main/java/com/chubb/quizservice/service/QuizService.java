package com.chubb.quizservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chubb.quizservice.dao.QuizDao;
import com.chubb.quizservice.feign.QuizInterface;
import com.chubb.quizservice.model.QuestionWrapper;
import com.chubb.quizservice.model.Quiz;
import com.chubb.quizservice.model.Response;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    private static final String CB_NAME = "questiondb";

    @CircuitBreaker(name = CB_NAME, fallbackMethod = "createQuizFallback")
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.findById(id).orElseThrow();
        List<Integer> questionIds = quiz.getQuestionIds();
        return quizInterface.getQuestionsFromId(questionIds);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        return quizInterface.getScore(responses);
    }

    public ResponseEntity<String> createQuizFallback(String category, int numQ, String title, Throwable t) {
        return new ResponseEntity<>("Question Service down. Try later.", HttpStatus.SERVICE_UNAVAILABLE);
    }
}