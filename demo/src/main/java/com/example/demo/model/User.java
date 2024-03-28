package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document("User")
public class User {
    private String name;
    private String email;
    private String password;
    
}
