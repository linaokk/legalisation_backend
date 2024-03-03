package com.pfa.apis;

import com.pfa.dtos.LoginDTO;
import com.pfa.dtos.RequestUpdateMyAccountDTO;
import com.pfa.dtos.SignupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RequestMapping("auth")
public interface AuthenticationApi {

    @PostMapping("login")
    ResponseEntity generateToken(@RequestBody LoginDTO data);

    @GetMapping("check_availability")
    ResponseEntity<Boolean> checkLoginAvailability(@RequestParam String login);

    @PostMapping("register")
    ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO);

    @GetMapping("get_user_info")
    ResponseEntity<?> getUserInfo();

    @GetMapping("get_my_account")
    ResponseEntity<?> getMyAccount();

    @PutMapping("update_my_account")
    ResponseEntity<?> updateMyAccount(@RequestBody RequestUpdateMyAccountDTO requestUpdateMyAccountDTO);
}
