package com.pfa.controllers;

import com.pfa.api.AdministratorApi;
import com.pfa.entities.ClientEntity;
import com.pfa.exceptions.UserNotFoundException;
import com.pfa.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        List<ClientEntity> clientEntities = this.adminService.fetchDisabledClients();
        return ResponseEntity.ok(clientEntities);
    }
}
