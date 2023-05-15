package com.pfa.controllers;


import com.pfa.api.AuthenticationApi;
import com.pfa.config.JwtTokenProvider;
import com.pfa.dtos.LoginDTO;
import com.pfa.dtos.SignupDTO;
import com.pfa.entities.ClientEntity;
import com.pfa.exceptions.UserNotFoundException;
import com.pfa.exceptions.UsernameAlreadyTakenException;
import com.pfa.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthenticationController implements AuthenticationApi {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity generateToken(LoginDTO data) throws UserNotFoundException {
        try {
            String login = data.getLogin();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    login,
                    data.getPassword()
            );
            Authentication authentication = authenticationManager.authenticate(authToken);
            String token = jwtTokenProvider.createToken(authentication);
            Map<Object, Object> model = new HashMap<>();
            model.put("username", login);
            model.put("token", token);
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
            ClientEntity clientEntity = this.userService.signup(signupDTO);
            return ResponseEntity.status(HttpStatus.OK).body(clientEntity);
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


}
