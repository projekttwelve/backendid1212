package com.ID1212HT22.ID1212.service;

import com.ID1212HT22.ID1212.exceptions.DataNotFoundException;
import com.ID1212HT22.ID1212.exceptions.EmailTakenException;
import com.ID1212HT22.ID1212.exceptions.UsernameNotFoundException;
import com.ID1212HT22.ID1212.exceptions.UsernameTakenException;
import com.ID1212HT22.ID1212.model.Player;
import com.ID1212HT22.ID1212.model.Question;
import com.ID1212HT22.ID1212.dao.PlayerDao;
import com.ID1212HT22.ID1212.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private final PlayerDao playerdao;
    Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseService(@Qualifier("postgres") PlayerDao playerdao){
        this.playerdao = playerdao;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Question> getAllQuestions(String type) {
        return playerdao.get_questions_of_a_type(type);
    }

    public List<String> getTypeOfQuestions() {
        return playerdao.get_type_of_questions();
    }

    public int get_quiz_id(String title) {
        return playerdao.get_quiz_id(title);
    }


    public int insertPerson(Player player) throws EmailTakenException, UsernameTakenException {
        String firstname = player.get_firstname();
        String lastname = player.get_lastname();
        String middlename = player.get_middlename();
        String email = player.get_email();
        String password = player.get_password();
        String gender = player.get_gender();
        String username = player.get_username();

        if(playerdao.getUsernameCount(username) != 0){
            logger.error("Username already exists");
            throw new UsernameTakenException("");
        }
        System.out.println("Do we see this 9.0");
        if(playerdao.getEmailCount(email) != 0){
            logger.error("Email already exists");
            throw new EmailTakenException("");
        }
        try{
            String encodedPassword = this.passwordEncoder.encode(player.get_password());
            player.setPassword(encodedPassword);
            return playerdao.insert_player(firstname, middlename, lastname, email, password, gender, username);
        }catch(Exception e){
            System.out.println(e);
            logger.error("Could not add person to database, check connection");
            return 0;
        }
    }
    public int insertResult(Result result) throws EmailTakenException, UsernameTakenException {
        int playerid = result.getPlayerid();
        int quizid = result.getQuizid();
        int score = result.getScore();
        try{
            return playerdao.insert_result(playerid, quizid, score);
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
    public int getPlayerId(String username) throws UsernameNotFoundException {
        try{
            int id = playerdao.get_playerid(username);
            return id;
        }catch(Exception e){
            logger.error("this user does not exist");
            throw new UsernameNotFoundException("");
        }
    }

    public List<Result> getResults(int playerid, int quizid) throws UsernameNotFoundException{
        try{
            List<Result> result = playerdao.getAllResults(playerid, quizid);
            return result;
        }catch(Exception e){
            System.out.println(e);
            logger.error("result does not exist");
        }
        return playerdao.getAllResults(playerid, quizid);
    }

    public String getUserId(String username) throws UsernameNotFoundException{
        try{
            String id = playerdao.get_userid(username);
            return id;
        }catch(Exception e){
            logger.error("this user does not exist");
            throw new UsernameNotFoundException("");
        }
    }

    public String[] getCredentials(String username) throws UsernameNotFoundException{
        String[] cred = new String[2];
        try{
            cred[0] = playerdao.get_email(username);
            cred[1] = playerdao.get_userid(username);
        }catch(Exception e){
            logger.error("CREDENTIALS NOT FOUND");
            throw new UsernameNotFoundException("");
        }
        return cred;
    }
    public Player getPlayerObject(String email) throws DataNotFoundException {
        String firstname = playerdao.get_firstname(email);
        String lastname = playerdao.get_lastname(email);
        String middlename = playerdao.get_middlename(email);
        String gender = playerdao.get_gender(email);
        String mail = playerdao.get_email(email);
        int playerid = playerdao.get_playerid(email);
        System.out.println(playerid);
        String password;
        String username;
        try{
            password = playerdao.get_password(email);
        }catch(Exception e){
            System.out.println(e);
            logger.error("No password found");
            password = "";
        }
        return new Player(firstname, lastname, middlename, mail, password, gender, email, playerid);
    }

}
