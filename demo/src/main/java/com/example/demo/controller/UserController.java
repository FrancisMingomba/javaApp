package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) throws Exception {
       return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return  ResponseEntity.ok(authenticationService.authenticate(request));
    }


    /*



    private static final ResponseEntity<Object> AuthenticationService = null;

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/welcome")
    public String welcome(){
        return ("i have got a  programmer job");
    }

    @GetMapping("/all")
    public String all(){
        return "Got it";
    }

    @GetMapping("/allUser")
     @PreAuthorize("hasAuthority('ADMIN')")
    public String allUser(){
        return authenticationServiceImpl.all().toString();
    }




    @PostMapping("/signup")
    public User signUp(@RequestBody @Valid User userFromClient)
            throws DuplicateUserException {
        return authenticationServiceImpl.signUp(userFromClient);
    }

    @GetMapping( "/getAllUsers")
   @RolesAllowed("USER")
    public ResponseEntity<Object> getAllUsers() {

        return ResponseEntity.ok(authenticationServiceImpl.getAllUsers());
        return ResponseEntity.ok("It works");
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody User user) {
        return userManager.addUser(user);
    }

    @PostMapping("/auth")

        public ResponseEntity<Object> login(@RequestBody User userFromClient)
                throws UserNotFoundException, AuthenticationException {
            return authenticationServiceImpl.login(userFromClient);
    }





    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") String id) {
        Optional<User> gOpt = userRepository.findById(id);
        return gOpt.orElse(null);
    }


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

    */
}
