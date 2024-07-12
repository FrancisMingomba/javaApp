package com.example.demo.controller;

import com.example.demo.config.JwtService;
import com.example.demo.model.User;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {

    JwtService jwtService = null;
    final String jwtKeyName = "x-auth-token";

   // public void authApi() {
       // jwtService = new JwtService();
   // }

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(

            @RequestBody RegisterRequest request
    ) throws Exception {
       // User signInUser = null;
       // signInUser = authenticationService.signup(request);

       // response.setHeader("Custom-Header", jwtService.generateToken(request));
       return ResponseEntity.ok(authenticationService.register(request));
             //  .header("x-auth-token",jwtService.generateToken(signInUser)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {

       return  ResponseEntity.ok(authenticationService.authenticate(request));

    }

    @PostMapping("/auth")
    public AuthenticationResponse login(@RequestBody User userFromClient)
            throws Exception{

        //return authenticationService.login(userFromClient);
        return authenticationService.login(userFromClient);

    }

   // @PostMapping("/reg")
   // public User reg(@RequestBody User request) throws Exception {
      //  return  authenticationService.signup(request);
   // }

    @PostMapping("/users")
    public ResponseEntity<Object> signup(@RequestBody User user)
    {

        User signedInUser = null;
        try
        {


            if (signedInUser == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected server error!");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }



        return ResponseEntity.status(HttpStatus.OK)
                .header(jwtKeyName, jwtService.generateToken(user))
                .body("");
    }


}
