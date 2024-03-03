package com.pfa.controllers;

import com.pfa.apis.AdministratorApi;
import com.pfa.dtos.RequestDTO;
import com.pfa.dtos.UserDTO;
import com.pfa.entities.users.UserEntity;
import com.pfa.exceptions.UserNotFoundException;
import com.pfa.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class AdministratorController implements AdministratorApi {

    private final AdminService adminService;

    @Override
    public ResponseEntity<?> fetchUsers() {
        List<UserEntity> clientEntities = adminService.fetchUsers();
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
        List<UserDTO> clientDTOs = this.adminService.fetchDisabledClients()
                .stream()
                .map(UserDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientDTOs);
    }

    @Override
    public ResponseEntity<?> fetchPendingRequests() {
        List<RequestDTO> requestsDTOs = this.adminService.fetchPendingRequests()
                .stream()
                .map(RequestDTO::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(requestsDTOs);
    }

    @Override
    public ResponseEntity<?> validateRequest(MultipartFile document, Long requestId) {
        try {
            this.adminService.validateRequest(
                    document,
                    requestId
            );
            return ResponseEntity.noContent().build();
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
                    .body("unhandled error : " + ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> refuseRequest(Long requestId) {
        this.adminService.refuseRequest(requestId);
        return ResponseEntity.noContent().build();
    }
}
