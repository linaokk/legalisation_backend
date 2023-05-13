package com.pfa.controllers;


import com.pfa.dtos.LoginDTO;
import com.pfa.dtos.SignupDTO;
import com.pfa.entities.*;
import com.pfa.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pfa.repository.ClientRepository;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {


    private final UserService userService;
    private final ClientRepository clientRepository;

    @PostMapping("signup")
    public ResponseEntity<Object> signup(@RequestBody SignupDTO signupDTO) {
        boolean loginAvailability = userService.checkLoginAvailability(signupDTO.getNom());
        if (loginAvailability) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body("xxxx");
        }
        Client client = this.userService.signup(signupDTO);

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }
    @PostMapping("login")
    public Client login(LoginDTO loginDTO){
        return this.userService.login(loginDTO);

    }


}
