package com.chubb.questionservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.chubb.questionservice.model.Question;
import com.chubb.questionservice.service.QuestionService;

@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {
	@Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionService service;

    @Test
    void testGetAllQuestions() throws Exception {
        when(service.getAllQuestions()).thenReturn(ResponseEntity.ok(List.of(new Question())));
        mockMvc.perform(get("/question/allQuestions")).andExpect(status().isOk());
    }

    @Test
    void testAddQuestion() throws Exception {
        when(service.addQuestion(any())).thenReturn(ResponseEntity.status(201).body("success"));
        mockMvc.perform(post("/question/add").contentType(MediaType.APPLICATION_JSON)
        		.content("{\"questionTitle\":\"test?\"}")).andExpect(status().isCreated());
    }
}
