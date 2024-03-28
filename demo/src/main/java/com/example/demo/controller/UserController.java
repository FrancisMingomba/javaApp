package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.DuplicateUserException;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationServiceImpl;

@Controller
@RequestMapping
public class UserController {
   

    //private static final ResponseEntity<Object> AuthentificationService = null;

    @Autowired
    AuthenticationServiceImpl authenticationServiceImpl;
    

    @PostMapping("/signup") 
    public AuthenticationServiceImpl signUp(@RequestBody User userFromClient) throws DuplicateUserException {
        return authenticationServiceImpl;
    }
    
}
