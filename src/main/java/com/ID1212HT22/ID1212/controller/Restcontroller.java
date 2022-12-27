package com.ID1212HT22.ID1212.controller;

import com.ID1212HT22.ID1212.exceptions.DataNotFoundException;
import com.ID1212HT22.ID1212.exceptions.EmailTakenException;
import com.ID1212HT22.ID1212.exceptions.UsernameNotFoundException;
import com.ID1212HT22.ID1212.exceptions.UsernameTakenException;
import com.ID1212HT22.ID1212.model.*;
import com.ID1212HT22.ID1212.service.DatabaseService;
import com.ID1212HT22.ID1212.service.MyUserDetailsService;
import com.ID1212HT22.ID1212.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* Controller for handling incoming HTTP requests.
* A RestController does not return any views. It only handles requests and formats responses.
* It will handle exceptions thrown by methods annotated with @RequestMapping or
*/
@RestController
public class Restcontroller {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;

    DatabaseService databaseservice;
    Logger logger = LoggerFactory.getLogger(Restcontroller.class);

    /**
    * Constructor which instanciates a databaseservice which communicates with the dao layer which in turn communicates with the database
    * @param  databaseservice A databaseservice object 
    */
    @Autowired
    public Restcontroller(DatabaseService databaseservice) {
        this.databaseservice = databaseservice;
    }

    @RequestMapping(value = "/api/ins", method = RequestMethod.POST)
    public int insertUser(@RequestBody Player player) throws EmailTakenException, UsernameTakenException {
        return databaseservice.insertPerson(player);
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public int insertResult(@RequestBody Result result) throws EmailTakenException, UsernameTakenException {
        System.out.println(result);
        return databaseservice.insertResult(result);
    }

    @RequestMapping(value = "/quizid/{title}", method = RequestMethod.GET)
    public int get_quiz_id(@PathVariable String title) throws Exception {
        try {
            return databaseservice.get_quiz_id(title);
        } catch (Exception e){
            System.out.println(e);
        }
        return databaseservice.get_quiz_id(title);
    }

    @RequestMapping(value = "/result/{quizid}/{playerid}", method = RequestMethod.GET)
    public List<Result> getResults(@PathVariable int playerid, @PathVariable int quizid) throws Exception {
        try {
            return databaseservice.getResults(playerid, quizid);
        } catch (Exception e){
            System.out.println(e);
        }
        return databaseservice.getResults(playerid, quizid);
    }

    @RequestMapping(value = "/questions/{type}", method = RequestMethod.GET)
    public List<Question> getQuestionsOfAType(@PathVariable String type) throws Exception {
        try {
            return databaseservice.getAllQuestions(type);
        } catch (Exception e){
           System.out.println(e);
        }
        return databaseservice.getAllQuestions(type);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public List<String> getTypesOfQuestions() throws Exception {
        try {
            return databaseservice.getTypeOfQuestions();
        } catch (Exception e){
            System.out.println(e);
        }
        return databaseservice.getTypeOfQuestions();
    }

    @RequestMapping(value = "/api/user/{email}", method = RequestMethod.GET)
    public Player getPlayerObject(@PathVariable String email) throws DataNotFoundException {
        return databaseservice.getPlayerObject(email);
    }

    @RequestMapping(value = "/userid/{username}", method = RequestMethod.GET)
    public int getUserId(@PathVariable String username) throws UsernameNotFoundException {
        return databaseservice.getPlayerId(username);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())
            );
        }catch(BadCredentialsException e){
            System.out.println(e);
            throw new Exception("incorrect credentials", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                authenticationRequest.getUsername()
        );
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        Player p;
        try{
            p = databaseservice.getPlayerObject(authenticationRequest.getUsername());
        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(new JwtPlayer(jwt, p));
    }
}
