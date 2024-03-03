package com.pfa.services;

import com.pfa.dtos.SignupAdminRequestDTO;
import com.pfa.entities.users.UserEntity;
import com.pfa.entities.users.enums.RoleEnum;
import com.pfa.exceptions.FeatureErrorEnum;
import com.pfa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperAdministratorService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public List<UserEntity> fetchAdministrators() {
        return this.userRepository.findByRole(RoleEnum.ROLE_ADMIN);
    }

    public void create(SignupAdminRequestDTO signupAdminRequestDTO) {
        String identityCode = signupAdminRequestDTO.getIdentityCode();
        this.userRepository.findByIdentityCode(identityCode)
                .ifPresent(x -> FeatureErrorEnum.FT0006.throwException());

        UserEntity userEntity = UserEntity.from(
                signupAdminRequestDTO,
                passwordEncoder,
                Boolean.FALSE,
                RoleEnum.ROLE_ADMIN,
                RoleEnum.ROLE_USER
        );
        this.userRepository.save(userEntity);
    }

    public void disableByIdentityCode(String identityCode) {
        UserEntity userEntity = this.userRepository.findByIdentityCode(identityCode)
                .orElseThrow(() -> FeatureErrorEnum.FT0001.newException());
        userEntity.setActive(Boolean.FALSE);
        this.userRepository.save(userEntity);
    }

    public void enableById(String identityCode) {
        UserEntity userEntity = this.userRepository.findByIdentityCode(identityCode)
                .orElseThrow(() -> FeatureErrorEnum.FT0001.newException());
        userEntity.setActive(Boolean.TRUE);
        this.userRepository.save(userEntity);
    }
}
