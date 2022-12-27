package com.ID1212HT22.ID1212.exceptions;

public class UsernameTakenException extends Exception{
    /**
    * Exception for when Username taken already.
    * @param  message message to be displayed to the offender
    */
    public UsernameTakenException(String message) {
        super(message);
    }
}
