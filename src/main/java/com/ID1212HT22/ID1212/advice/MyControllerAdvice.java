package com.ID1212HT22.ID1212.advice;

import com.ID1212HT22.ID1212.exceptions.EmailTakenException;
import com.ID1212HT22.ID1212.exceptions.UsernameTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
* Global exception handling...
* @ControllerAdvice allows us to handle exceptions globally
* It will handle exceptions thrown by methods annotated with @RequestMapping or
*   Get
*   Post
*   Delete
*   Patch
* Mappings.
*/
@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(value = {UsernameTakenException.class})
    public ResponseEntity<String> usernameTakenException(UsernameTakenException e){
        return new ResponseEntity<String>("Username is taken...", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmailTakenException.class})
    public ResponseEntity <String> emailTakenException(EmailTakenException e){
        return new ResponseEntity<String>("Email is taken", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity <String> genericException(Exception e){
        return new ResponseEntity<String>("Something went wrong :/", HttpStatus.BAD_REQUEST);
    }
}
