package com.example.authenticationservice.controllers;

import com.example.authenticationservice.dtos.LoginRequestDto;
import com.example.authenticationservice.models.User;
import com.example.authenticationservice.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthenticationController {
    private AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }
    //login-->Takes email and password via LoginRequestDto and checks in DB if any user available
    //with the given email. If found returns the session token of the user.
    //If not found creates a new user, and generates a new token and returns the token
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto){
        User user = new User();
        user.setEmail(loginRequestDto.getEmail());
        user.setPassword(loginRequestDto.getPassword());
        ResponseEntity<String> responseEntity = authenticationService.login(user);
        return new ResponseEntity<>(responseEntity.getBody(), responseEntity.getStatusCode());
    }
    //Validate API accepts a token via pathvariable and checks in DB if the token is
    //available in DB. If not available returns "not available" else returns "available"
    @GetMapping("/validate/{token}")
    public ResponseEntity<String> validate(@PathVariable("token") String token){
        ResponseEntity<String> responseEntity = authenticationService.validate(token);
        return new ResponseEntity<>(responseEntity.getBody(), responseEntity.getStatusCode());
    }
}
