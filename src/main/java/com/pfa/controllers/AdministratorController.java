package com.pfa.controllers;

import com.pfa.apis.AdministratorApi;
import com.pfa.dtos.ClientDTO;
import com.pfa.entities.ClientEntity;
import com.pfa.exceptions.UserNotFoundException;
import com.pfa.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class AdministratorController implements AdministratorApi {

    private final AdminService adminService;

    @Override
    public ResponseEntity<?> fetchUsers() {
        List<ClientEntity> clientEntities = adminService.fetchUsers();
        return ResponseEntity.ok(clientEntities);
    }

    @Override
    public ResponseEntity<?> enableUser(String login) {
        try {
            adminService.enableClient(login);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> disableUser(String login) {
        try {
            adminService.disableClient(login);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<?> fetchDisabledClients() {
        List<ClientDTO> clientDtos = this.adminService.fetchDisabledClients()
                .stream()
                .map(ClientDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientDtos);
    }
}
