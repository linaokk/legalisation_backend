package com.pfa.controllers;


import com.pfa.dtos.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import repository.UserRepository;

@RestController
public class AuthenticationController {
    @Autowired
        UserRepository  userRepository;
    @PostMapping("signup")
    public ResponseEntity<Object> signup(@RequestBody SignupDTO signupDTO) {
        userRepository.findAll();
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }
}
