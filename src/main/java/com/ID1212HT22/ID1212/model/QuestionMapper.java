package com.ID1212HT22.ID1212.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setAnswer(rs.getString("answer"));
        question.setWrong_answer1(rs.getString("wrong_answer1"));
        question.setWrong_answer2(rs.getString("wrong_answer2"));
        question.setScore(rs.getInt("score"));
        question.setText(rs.getString("text"));
        question.setType(rs.getString("type"));
        return question;
    }

}
