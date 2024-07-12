package com.example.demo.controller;

import com.example.demo.config.JwtService;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.userService.RegisterValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static io.jsonwebtoken.Jwts.header;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RegisterValidation registerValidation;

    public AuthenticationResponse register(RegisterRequest request) {



        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        boolean exist = Boolean.parseBoolean(String.valueOf(registerValidation.userAlreadyExist(user.getEmail())));


        if(exist){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build()

        ;
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
                var user = userRepository.findUserByEmail(request.getEmail())
                        .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(@RequestBody User user) throws Exception {
        Optional<User> userInDb = userRepository.findUserByEmail(user.getEmail());

        if (userInDb == null)
            throw new Exception("AppUser does not exist");


        if (!userInDb.isPresent())
            throw new Exception("Wrong username or password");

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
    /*

    public User signup(RegisterRequest user) throws Exception {
        if (alreadyExists(user))
            throw new Exception("User already exists");

        // lower the case for email
        user.setEmail(user.getEmail().toLowerCase());

        // this.userRepository.create(user);
        //this.userRepository.save(user);

        return user;
    }

     */

    private boolean alreadyExists(RegisterRequest user) {
        return this.userRepository.findUserByEmail(user.getEmail()) != null;
    }
}
