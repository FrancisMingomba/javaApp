package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.error.AuthenticationException;
import com.example.demo.error.DuplicateUserException;
import com.example.demo.error.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private UserRepository userRepository;

    public User signUp(User userFromClient) throws DuplicateUserException {
        if (alreadyExists(userFromClient)) {
            throw new DuplicateUserException("User alredy existe");
        }

        return this.userRepository.save(userFromClient);
        
    }

    private boolean alreadyExists(User user) {
        return this.userRepository.findUserByEmail(user.getEmail()) != null;
    }

    @Override
    public ResponseEntity<Object> login(User user) throws UserNotFoundException, AuthenticationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public ResponseEntity<Object> logout(User user) throws UserNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }


    
}
