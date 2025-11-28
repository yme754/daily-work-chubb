package com.chubb.questionservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionWrapper {
	private Integer id;
    private String questionTitle;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    public QuestionWrapper(Integer id, String questionTitle, String op1, String op2, String op3, String op4) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
    }
}
