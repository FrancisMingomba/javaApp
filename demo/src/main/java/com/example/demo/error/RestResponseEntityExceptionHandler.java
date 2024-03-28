package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

       @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException exception,
            WebRequest request) {
        return badRequest(exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> authenticationException(AuthenticationException exception,
            WebRequest request) {
        return badRequest(exception.getMessage());
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<String> authenticationException(DuplicateUserException exception,
            WebRequest request) {
        return badRequest(exception.getMessage());
    }

    // @ExceptionHandler(PaymentFailedException.class)
    // public ResponseEntity<String> paymentFailedException(PaymentFailedException
    // exception,
    // WebRequest request) {
    // return badRequest(exception.getMessage());
    // }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentException(DuplicateUserException exception,
            WebRequest request) {
        return badRequest(exception.getMessage());
    }

    @ExceptionHandler(UnknownApplicationTypeException.class)
    public ResponseEntity<String> unknownVisaTypeException(DuplicateUserException exception,
            WebRequest request) {
        return badRequest(exception.getMessage());
    }

    private ResponseEntity<String> badRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(message);
    }
    
}
