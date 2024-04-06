package com.example.demo.controller;

import com.example.demo.error.AuthenticationException;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.DuplicateUserException;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static javax.swing.text.html.parser.DTDConstants.ID;

@RestController
@RequestMapping("")
public class UserController {
    //private static final ResponseEntity<Object> AuthenticationService = null;

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public User signUp(@RequestBody @Valid User userFromClient)
            throws DuplicateUserException {
        return authenticationServiceImpl.signUp(userFromClient);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Object> getAllUsers() {

        return ResponseEntity.ok(authenticationServiceImpl.getAllUsers());
    }

    @PostMapping("/auth")

        public ResponseEntity<Object> login(@RequestBody User userFromClient)
                throws UserNotFoundException, AuthenticationException {
            return authenticationServiceImpl.login(userFromClient);
    }

    //------------------------------------------------------------------------

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") String id) {
        Optional<User> gOpt = userRepository.findById(id);
        if (gOpt.isPresent()) {
            return gOpt.get();
        }
        return null;
    }


    //------------------------------------------------------------------------
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)

    public Map<String, String> handlaValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }


}
