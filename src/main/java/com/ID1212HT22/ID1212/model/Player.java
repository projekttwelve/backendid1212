package com.ID1212HT22.ID1212.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
* Person object representing a real person.
* The class is used for functionality where a player object might be useful for example inserting a person into the database.
*/
public class Player {
    protected String firstname;
    protected String lastname;
    protected String middlename;
    protected String email;
    protected String password;
    protected String gender;
    protected String username;

    public void setUserid(int playerid) {
        this.playerid = playerid;
    }

    protected int playerid;


    /**
    * Constructor setting various values of a person.
    * @JsonProperty is used so that we can create a person using json filled requests.
    * @param  firstname of the person
    * @param  lastname of the person
    * @param  middlename of the person
    * @param  email that the person has.
    * @param  password that the person has.
    * @param  gender that the person has.
    */
    public Player(
            @JsonProperty("firstname") String firstname,
            @JsonProperty("lastname") String lastname,
            @JsonProperty("middlename") String middlename,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("gender") String gender,
            @JsonProperty("username") String username) {
                this.firstname = firstname;
                this.lastname = lastname;
                this.middlename = middlename;
                this.email = email;
                this.password = password;
                this.gender = gender;
                this.username = username;
    }
    public Player(
    String firstname,
    String lastname,
    String middlename,
    String email,
    String password,
    String gender,
    String username,
    int playerid){
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.username = username;
        this.playerid = playerid;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getPlayerid() {
        return playerid;
    }

    public String get_firstname(){return this.firstname;}
    public String get_middlename(){return this.middlename;}
    public String get_lastname(){return this.lastname;}
    public String get_email(){return this.email;}
    public String get_password(){return this.password;}
    public String get_gender(){return this.gender;}
    public String get_username(){return this.username;}
}
