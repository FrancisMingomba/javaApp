package com.example.demo.userService;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterValidation {

    @Autowired
    private UserRepository userRepository;

    public boolean userAlreadyExist(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.isPresent();

    }
}
