package com.pfa.controllers;


import com.pfa.apis.AuthenticationApi;
import com.pfa.dtos.LoginDTO;
import com.pfa.dtos.RequestUpdateMyAccountDTO;
import com.pfa.dtos.SignupDTO;
import com.pfa.dtos.UserDTO;
import com.pfa.entities.users.UserEntity;
import com.pfa.exceptions.UsernameAlreadyTakenException;
import com.pfa.services.AuthenticationService;
import com.pfa.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity generateToken(LoginDTO data) {
        try {
            Map<Object, Object> model = this.authenticationService.loginAndGenerateToken(data);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }


    @Override
    public ResponseEntity<Boolean> checkLoginAvailability(String login) {
        boolean availability = this.userService.checkLoginAvailability(login);
        return ResponseEntity.ok(availability);
    }

    @Override
    public ResponseEntity<?> signup(SignupDTO signupDTO) {
        try {
            UserEntity userEntity = this.userService.signup(signupDTO);
            return ResponseEntity.status(HttpStatus.OK).body(userEntity);
        } catch (UsernameAlreadyTakenException ex) {
            return ResponseEntity.status(ex.getHttpCode()).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
                    .body("unhandled error : " + ex.getMessage());
        }
    }


    @Override
    public ResponseEntity<?> getUserInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Map<String, Object> userInfo = Map.of(
                "principal", authentication.getPrincipal(),
                "login", authentication.getName(),
                "credentials", authentication.getCredentials(),
                "authorities", authentication.getAuthorities()
        );
        return ResponseEntity.ok(userInfo);
    }

    @Override
    public ResponseEntity<?> getMyAccount() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        String identityCode = principal.getUsername();

        UserEntity userEntity = this.authenticationService.findClientByIdentityCode(identityCode);
        UserDTO clientDTO = UserDTO.from(userEntity);
        return ResponseEntity.ok(clientDTO);
    }

    @Override
    public ResponseEntity<?> updateMyAccount(RequestUpdateMyAccountDTO requestUpdateMyAccountDTO) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        String identityCode = principal.getUsername();

        this.authenticationService.updateClientAccount(
                identityCode,
                requestUpdateMyAccountDTO.getPhoneNumber(),
                requestUpdateMyAccountDTO.getPassword(),
                requestUpdateMyAccountDTO.getEmail()
        );

        return ResponseEntity.noContent().build();
    }


}
