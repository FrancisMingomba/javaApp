package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.error.AuthenticationException;
import com.example.demo.error.DuplicateUserException;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.mockito.internal.matchers.text.ValuePrinter.print;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private UserRepository userRepository;

    public User signUp(User userFromClient) throws DuplicateUserException {
        if (alreadyExists(userFromClient))
            throw new DuplicateUserException("User already exist-->");

        return this.userRepository.save(userFromClient);
    }

    @Override
    public List<User> getAllUsers() {
        Iterable<User> userIdb = this.userRepository.findAll();
        List<User> data = convertUsersToList(userIdb);
        return data;
    }

    @Override
    public List<User> all() {
        List<User> userIdb = this.userRepository.findAll();
        return userIdb;
    }


    private List<User> convertUsersToList(Iterable<User> users) {
        return StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toList());
    }

    private boolean alreadyExists( User user) {

        return this.userRepository.findUserByEmail(user.getEmail()) != null;
    }

    /*
    @Override
    public ResponseEntity<Object> login(User user) throws UserNotFoundException, AuthenticationException {
        User userInDb = this.userRepository.findUserByEmail(user.getEmail().toLowerCase());

        if (userInDb == null)
            throw new UserNotFoundException("Wrong username or password!");

        if (!userInDb.isAuthentic(user))
            throw new AuthenticationException("Wrong username or password!");

       return ResponseEntity.ok(userInDb);



    }

     */

    @Override
    public ResponseEntity<Object> logout(User user) throws UserNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }


    /*
    public List<User> getUserId(int id) {
        return userList.stream()
                .filter(user -> user.getUserId()==id)
                .findAny()
                .orElseThrow(()->new RuntimePermission("User" + "id" + "not found"));


    }
    */

    
}
