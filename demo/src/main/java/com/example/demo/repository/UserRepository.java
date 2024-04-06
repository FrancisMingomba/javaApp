package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    User findUserByEmail(String email);

    Optional<User> findById(String id);
    //User findById(String ID);
}
