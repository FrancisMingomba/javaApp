package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.error.AuthenticationException;
import com.example.demo.error.DuplicateUserException;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.model.User;

public interface AuthenticationService {

    public User signUp(User user) throws DuplicateUserException;

        public ResponseEntity<Object> login(User user) throws UserNotFoundException, AuthenticationException;

        public ResponseEntity<Object> logout(User user) throws UserNotFoundException;
    
}