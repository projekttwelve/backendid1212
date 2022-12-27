package com.ID1212HT22.ID1212.exceptions;

public class UsernameNotFoundException extends Exception{
    /**
    * Exception for when Username is not found for a user.
    * @param  message message to be displayed to the offender
    */
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
