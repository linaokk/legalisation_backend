package com.pfa.services;

import com.pfa.entities.users.UserEntity;
import com.pfa.entities.users.enums.RoleEnum;
import com.pfa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperAdministratorService {

    private final UserRepository userRepository;


    public List<UserEntity> fetchAdministrators() {
        return this.userRepository.findByRole(RoleEnum.ROLE_ADMIN);
    }

}
