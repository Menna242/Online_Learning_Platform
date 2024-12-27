// mariam
package com.example.demo.Request;

import jakarta.validation.constraints.NotNull;

public class AnswerDTO {
    private int questionID;
    @NotNull
    private String answer;

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // Getters and Setters
}
