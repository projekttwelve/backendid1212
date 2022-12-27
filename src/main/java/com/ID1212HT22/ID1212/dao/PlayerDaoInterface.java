package com.ID1212HT22.ID1212.dao;

import com.ID1212HT22.ID1212.exceptions.EmailTakenException;
import com.ID1212HT22.ID1212.exceptions.UsernameTakenException;
import com.ID1212HT22.ID1212.model.Question;
import com.ID1212HT22.ID1212.model.Result;

import java.util.List;

/**
* PersonDaoInterface for interacting with the database.
*/
public interface PlayerDaoInterface {
    int get_quiz_id(String title);

    Result get_results(int quizid, int playerid);

    int insert_result(int playerid, int quizid, int score);

    String get_firstname(String firstname);
    String get_lastname(String lastname);
    String get_middlename(String middlename);

    String get_username(String username);

    String get_email(String email);
    String get_gender(String gender);

    List<Result> getAllResults(int playerid, int quizid);

    String get_password(String password);
    int insert_player(String firstname, String lastname, String middlename, String email, String password, String gender, String username);
    String get_userid(String email);

    int getEmailCount(String email) throws EmailTakenException;

    int getUsernameCount(String username) throws UsernameTakenException;

    int get_playerid(String username);

    List<Question> get_questions_of_a_type(String type);

    List<String> get_type_of_questions();
}
