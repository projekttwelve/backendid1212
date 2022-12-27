package com.ID1212HT22.ID1212.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
    private int quizid;
    private int playerid;

    private int score;

    private String time;

    public int getQuizid() {
        return quizid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setQuizid(int quizid) {
        this.quizid = quizid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPlayerid() {
        return playerid;
    }

    public Result(int quizid, int playerid, int score, String time) {
        this.quizid = quizid;
        this.playerid = playerid;
        this.score = score;
        this.time = time;
    }
    public Result(
            @JsonProperty("quizid") int quizid,
            @JsonProperty("playerid") int playerid,
            @JsonProperty("score") int score) {
            this.quizid = quizid;
        this.playerid = playerid;
        this.score = score;
    }
}
