package com.ID1212HT22.ID1212.exceptions;

public class EmailTakenException extends Exception{
    /**
    * Exception for when email is taken for a user.
    * @param  message message to be displayed to the offender
    */
    public EmailTakenException(String message) {
        super(message);
    }
}
