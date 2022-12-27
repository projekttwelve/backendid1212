package com.ID1212HT22.ID1212.dao;

import com.ID1212HT22.ID1212.exceptions.EmailTakenException;
import com.ID1212HT22.ID1212.exceptions.UsernameTakenException;
import com.ID1212HT22.ID1212.model.Question;
import com.ID1212HT22.ID1212.model.QuestionMapper;
import com.ID1212HT22.ID1212.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
* Person-Access Object implementing the DaoInterface.
* This class contains methods for directly interacting with the database.
* Classes wanting to use this Dao should be placed in the service package.
*/
@Repository("postgres")
public class PlayerDao implements PlayerDaoInterface {

    private final JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(PlayerDao.class);

    /**
    * Constructor setting jdbcTemplate
    * @param  jdbctemplate object used for writing queries.
    */
    @Autowired
    public PlayerDao(JdbcTemplate jdbctemplate){
        this.jdbcTemplate = jdbctemplate;
    }

    @Override
    public int getEmailCount(String email) throws EmailTakenException {
        String sqlEmailTaken = "SELECT COUNT(*) FROM player WHERE email = ?";
        int emailTaken = jdbcTemplate.queryForObject(sqlEmailTaken, new Object[] {email}, Integer.class);
        return emailTaken;
    }

    /**
     * Counts the number of same usernames in db
     * @param username String
     * @return the number of same usernames
     * @throws UsernameTakenException
     */
    @Override
    public int getUsernameCount(String username) throws UsernameTakenException{
        String sqlUsernameTaken = "SELECT COUNT(*) FROM player WHERE username = ?";
        int usernameTaken = jdbcTemplate.queryForObject(sqlUsernameTaken, new Object[] {username}, Integer.class);
        return usernameTaken;
    }

    /**
     * Inserts a person object into the db
     * @returns 1 if it is successfull
     */
    @Override
    public int insert_player(String firstname, String lastname, String middlename, String email, String password, String gender, String username){
        int sqlReturnValue = jdbcTemplate.update(
                "INSERT INTO player(firstname, lastname, middlename, email, password, gender, username) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                firstname, lastname, middlename, email, password, gender, username);
        return sqlReturnValue;
    }

    @Override
    public int get_quiz_id(String type){
        String sql = "SELECT id FROM quiz WHERE type = ?";
        int strid = jdbcTemplate.queryForObject(sql, new Object[] {type}, Integer.class);
        return strid;
    }

    @Override
    public Result get_results(int quizid, int playerid){
        String sql = "SELECT score FROM results WHERE id = ? AND quiz = ?";
        int score = jdbcTemplate.queryForObject(sql, new Object[] {playerid, quizid}, Integer.class);
        Result result = new Result(quizid, playerid, score);
        return result;
    }

    @Override
    public int insert_result(int playerid, int quizid, int score){
        int sqlReturnValue = jdbcTemplate.update(
                "INSERT INTO results(id, quiz, score) " +
                        "VALUES (?, ?, ?)",
                playerid, quizid, score);
        return sqlReturnValue;
    }

    @Override
    public String get_firstname(String username){
        String sqlString = "SELECT firstname FROM player WHERE username = ?";
        String name = jdbcTemplate.queryForObject(sqlString, new Object[] {username}, String.class);
        return name;
    }
    @Override
    public String get_middlename(String username){
        String sqlString = "SELECT middlename FROM player WHERE username = ?";
        String middlename = jdbcTemplate.queryForObject(sqlString, new Object[] {username}, String.class);
        return middlename;
    }
    @Override
    public String get_lastname(String username){
        String sqlString = "SELECT firstname FROM player WHERE username = ?";
        String lastname = jdbcTemplate.queryForObject(sqlString, new Object[] {username}, String.class);
        return lastname;
    }
    @Override
    public String get_password(String username){
        String sqlString = "SELECT password FROM player WHERE username = ?";
        String password = jdbcTemplate.queryForObject(sqlString, new Object[] {username}, String.class);
        return password;
    }
    @Override
    public List<Result> getAllResults(int playerid, int quizid){
        String sql = "SELECT * FROM results WHERE id = ? AND quiz = ?";
        List<Result> results = jdbcTemplate.query(sql, new Object[] {playerid, quizid},
                (rs, rowNum) ->
                       new Result(rs.getInt("quiz"), rs.getInt("id"),rs.getInt("score"), rs.getString("timestamp"))

        );
        return results;
    }
    @Override
    public String get_username(String username){
        String sqlString = "SELECT username FROM player WHERE username = ?";
        String user = jdbcTemplate.queryForObject(sqlString, new Object[] {username}, String.class);
        return user;
    }

    @Override
    public String get_email(String username){
        String sqlString = "SELECT email FROM player WHERE username = ?";
        String mail = jdbcTemplate.queryForObject(sqlString, new Object[] {username}, String.class);
        return mail;
    }

    public String get_gender(String username){
        String sqlString = "SELECT gender FROM player WHERE username = ?";
        String gender = jdbcTemplate.queryForObject(sqlString, new Object[] {username}, String.class);
        return gender;
    }

    @Override
    public int get_playerid(String username){
        String sql2 = "SELECT id FROM player WHERE username = ?";
        int id = jdbcTemplate.queryForObject(sql2, new Object[] {username}, Integer.class);
        return id;
    }

    @Override
    public String get_userid(String username){
        String sql2 = "SELECT id FROM player WHERE username = ?";
        String id = jdbcTemplate.queryForObject(sql2, new Object[] {username}, String.class);
        return id;
    }
    @Override
    public List<Question> get_questions_of_a_type(String type){
        String sql = "SELECT * FROM question WHERE type = '" + type + "'";
        List <Question> questions = jdbcTemplate.query(sql, new QuestionMapper());
        return questions;
    }

    @Override
    public List<String> get_type_of_questions(){
        String sql = "SELECT * FROM typeofquestion";
        List <String> types = jdbcTemplate.queryForList(sql, String.class);
        return types;
    }
}
