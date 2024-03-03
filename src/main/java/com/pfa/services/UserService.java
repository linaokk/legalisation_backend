package com.pfa.services;

import com.pfa.dtos.LoginDTO;
import com.pfa.dtos.SignupDTO;
import com.pfa.entities.users.UserEntity;
import com.pfa.entities.users.enums.RoleEnum;
import com.pfa.exceptions.FeatureErrorEnum;
import com.pfa.exceptions.UserNotFoundException;
import com.pfa.exceptions.UsernameAlreadyTakenException;
import com.pfa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean checkLoginAvailability(String login) {
        return !userRepository.findByLogin(login).isPresent();
    }


    public UserEntity signup(SignupDTO signupDTO) throws UsernameAlreadyTakenException {
        boolean loginAvailability = this.checkLoginAvailability(signupDTO.getIdentityCode());
        if (!loginAvailability) {
            throw new UsernameAlreadyTakenException();
        }

        UserEntity clientEntity = UserEntity.from(signupDTO, passwordEncoder);
        return userRepository.save(clientEntity);
    }


    public UserEntity login(LoginDTO loginDTO) throws UserNotFoundException {
        Optional<UserEntity> optClient = this.userRepository.findByLogin(loginDTO.getLogin());
        UserEntity clientEntity = optClient.orElseThrow(() -> new RuntimeException("le client nexiste pas"));
        clientEntity.setUserPicture(loginDTO.getUserPicture());
        this.userRepository.save(clientEntity);
        return clientEntity;
    }


}
