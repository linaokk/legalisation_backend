package com.pfa.controllers;

import com.pfa.apis.SuperAdministratorApi;
import com.pfa.dtos.SignupAdminRequestDTO;
import com.pfa.dtos.SignupDTO;
import com.pfa.dtos.UserDTO;
import com.pfa.entities.users.UserEntity;
import com.pfa.services.SuperAdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SuperAdministratorController implements SuperAdministratorApi {

    private final SuperAdministratorService superAdministratorService;

    @Override
    public List<UserDTO> fetchAdministrators() {
        List<UserEntity> fetchAdministrators = superAdministratorService.fetchAdministrators();
        return fetchAdministrators.stream()
                .map(UserDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public void enableAdministrator(String identityCode) {
        this.superAdministratorService.enableById(identityCode);
    }

    @Override
    public void disableAdministrator(String identityCode) {
        this.superAdministratorService.disableByIdentityCode(identityCode);
    }

    @Override
    public void addAdministrator(SignupAdminRequestDTO signupAdminRequestDTO) {
        this.superAdministratorService.create(signupAdminRequestDTO);
    }

}
