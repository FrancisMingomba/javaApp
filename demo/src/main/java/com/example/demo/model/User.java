package com.example.demo.model;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class User {

    //@Valid

    @NotNull(message = "Must not be null")
    @NotBlank(message = "Must not be blank")
    @NotEmpty(message = "Must not be Empty")
    private String name;

    @NotNull(message = "Must not be null")
    @Email(message = "Invalid email")
    @NotEmpty(message = "Must not be empty")
    private String email;

    @NotNull(message = "Must not be null")
    @NotBlank(message = "Must not be blank")
    @NotEmpty(message = "Must not be empty")
    private String password;


    public boolean isAuthentic(User user) {
        return email.equals(user.getEmail()) && password.equals(user.getPassword());
    }


}
