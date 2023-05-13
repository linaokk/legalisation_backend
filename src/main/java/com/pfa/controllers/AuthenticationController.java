package com.pfa.controllers;


import com.pfa.dtos.LoginDTO;
import com.pfa.dtos.SignupDTO;
import com.pfa.entities.*;
import com.pfa.exceptions.UserNotFoundException;
import com.pfa.exceptions.UsernameAlreadyTakenException;
import com.pfa.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    
    @GetMapping
    public ResponseEntity<Boolean> checkLoginAvailability(@RequestParam String login){
        boolean availability = this.userService.checkLoginAvailability(login);
        return ResponseEntity.ok(availability);
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO) {
        try {
            ClientEntity clientEntity = this.userService.signup(signupDTO);
            return ResponseEntity.status(HttpStatus.OK).body(clientEntity);
        } catch (UsernameAlreadyTakenException ex) {
            return ResponseEntity.status(ex.getHttpCode()).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
                    .body("unhandled error : " + ex.getMessage());
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            ClientEntity clientEntity = this.userService.login(loginDTO);
            return ResponseEntity.ok(clientEntity);

        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(ex.getHttpCode()).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
                    .body("unhandled error : " + ex.getMessage());
        }
    }


}
