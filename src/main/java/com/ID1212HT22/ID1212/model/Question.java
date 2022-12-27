package com.ID1212HT22.ID1212.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {

        protected int id;
        protected String text;
        protected String answer;
        protected String wrong_answer1;
        protected String wrong_answer2;
    protected String type;
        protected int score;

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    public String getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public String getWrong_answer1() {
        return wrong_answer1;
    }

    public void setWrong_answer1(String wrong_answer1) {
        this.wrong_answer1 = wrong_answer1;
    }

    public void setWrong_answer2(String wrong_answer2) {
        this.wrong_answer2 = wrong_answer2;
    }

    public String getWrong_answer2() {
        return wrong_answer2;
    }

    public Question(){}
    public Question(
                @JsonProperty("id") int id,
                @JsonProperty("text") String text,
                @JsonProperty("answer") String answer,
                @JsonProperty("wrong_answer1") String wrong_answer1,
                @JsonProperty("wrong_answer2") String wrong_answer2,
                @JsonProperty("type") String type,
                @JsonProperty("score") int score){
            this.id = id;
            this.text = text;
            this.answer = answer;
            this.wrong_answer1 = wrong_answer1;
            this.wrong_answer2 = wrong_answer2;
            this.type = type;
            this.score = score;
        }
}
