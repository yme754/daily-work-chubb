package com.chubb.quizservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.chubb.quizservice.model.QuizDto;
import com.chubb.quizservice.service.QuizService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuizControllerTest {
	@MockBean
    QuizService quizService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testCreateQuizEndpoint() throws Exception {
        QuizDto dto = new QuizDto();
        dto.setCategoryName("Java");
        dto.setNumQuestions(5);
        dto.setTitle("Java Quiz");
        when(quizService.createQuiz(any(),any(),any())).thenReturn(ResponseEntity.status(201).body("Success"));
        mockMvc.perform(post("/quiz/create").contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(dto))).andExpect(status().isCreated());
    }

    @Test
    void testSubmitQuizEndpoint() throws Exception {
        when(quizService.calculateResult(any(),any())).thenReturn(ResponseEntity.ok(5));
        mockMvc.perform(post("/quiz/submit/1").contentType("application/json")
                .content("[{\"id\":1,\"response\":\"A\"}]")).andExpect(status().isOk());
    }
}
