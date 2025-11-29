package com.chubb.quizservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.chubb.quizservice.model.QuestionWrapper;
import com.chubb.quizservice.model.Response;

@FeignClient(name = "QUESTION-SERVICE", url = "http://localhost:8086")
public interface QuizInterface {
	
	@GetMapping("/question/getQuestionsForQuiz/{category}/{numQ}")
    ResponseEntity<List<Integer>> getQuestionsForQuiz(
            @PathVariable String category,
            @PathVariable int numQ);
	
	@GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions );

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
