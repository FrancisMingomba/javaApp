package com.example.demo.controller;

import com.example.demo.error.AuthenticationException;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.repository.UserRepository;
//import jakarta.annotation.security.RolesAllowed;
import com.example.demo.userservice.UserManager;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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

import static org.apache.coyote.http11.Constants.a;

@RestController
@RequestMapping("/api")
public class UserController {
    //private static final ResponseEntity<Object> AuthenticationService = null;

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserManager userManager;

    public UserController(AuthenticationServiceImpl authenticationServiceImpl) {
        this.authenticationServiceImpl = authenticationServiceImpl;
    }


    @GetMapping("/welcome")
    public String welcome(){
        return ("i have got a  programmer job");
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
    public String all(){
        return "Got it";
    }

    @GetMapping("/allUser")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public String allUser(){
        return authenticationServiceImpl.all().toString();
    }




    @PostMapping("/signup")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User signUp(@RequestBody @Valid User userFromClient)
            throws DuplicateUserException {
        return authenticationServiceImpl.signUp(userFromClient);
    }

    @GetMapping( "/getAllUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
   //@RolesAllowed("USER")
    public ResponseEntity<Object> getAllUsers() {

        return ResponseEntity.ok(authenticationServiceImpl.getAllUsers());
        //return ResponseEntity.ok("It works");
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody User user) {
        return userManager.addUser(user);
    }
    /*
    @PostMapping("/auth")

        public ResponseEntity<Object> login(@RequestBody User userFromClient)
                throws UserNotFoundException, AuthenticationException {
            return authenticationServiceImpl.login(userFromClient);
    }

     */

    //------------------------------------------------------------------------

    @GetMapping("/getUserById/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public User getUserById(@PathVariable("id") String id) {
        Optional<User> gOpt = userRepository.findById(id);
        return gOpt.orElse(null);
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
