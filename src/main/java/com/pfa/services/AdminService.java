package com.pfa.services;

import com.pfa.entities.requests.RequestEntity;
import com.pfa.entities.requests.RequestStatusEnum;
import com.pfa.entities.users.UserEntity;
import com.pfa.exceptions.FeatureErrorEnum;
import com.pfa.exceptions.UserNotFoundException;
import com.pfa.repository.RequestRepository;
import com.pfa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final PasswordEncoder passwordEncoded;

    public List<UserEntity> fetchUsers() {
        return userRepository.findAll();
    }

    public void enableClient(String login) throws UserNotFoundException {
        UserEntity clientEntity = userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException());
        clientEntity.setActive(true);
        userRepository.save(clientEntity);
    }

    public void disableClient(String login) throws UserNotFoundException {
        UserEntity clientEntity = userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException());
        clientEntity.setActive(false);
        userRepository.save(clientEntity);
    }

    public List<UserEntity> fetchDisabledClients() {
        return userRepository.fetchDisabledClients();
    }

    public void createDefaultAdministrator() {
        UserEntity administratorEntity = UserEntity.buildAdministrator(passwordEncoded);
        String username = administratorEntity.getUsername();
        boolean exists = this.userRepository.findByLogin(username).isPresent();
        if (exists) return;
        this.userRepository.save(administratorEntity);
    }

    public void createDefaultSuperAdministrator() {
        UserEntity administratorEntity = UserEntity.buildSuperAdministrator(passwordEncoded);
        String username = administratorEntity.getUsername();
        boolean exists = this.userRepository.findByLogin(username).isPresent();
        if (exists) return;
        this.userRepository.save(administratorEntity);
    }

    public List<RequestEntity> fetchPendingRequests() {
        return this.requestRepository.findByStatus(RequestStatusEnum.INITIAL_REQUEST);
    }

    public void validateRequest(MultipartFile document, Long requestId) throws IOException {
        RequestEntity requestEntity = this.requestRepository.findById(requestId)
                .orElseThrow(() -> FeatureErrorEnum.FT0005.newException());
        requestEntity.setDocument(document.getResource().getContentAsByteArray());
        requestEntity.setStatus(RequestStatusEnum.VALIDATED);
        this.requestRepository.save(requestEntity);
    }

    public void refuseRequest(Long requestId) {
        RequestEntity requestEntity = this.requestRepository.findById(requestId)
                .orElseThrow(() -> FeatureErrorEnum.FT0005.newException());

        requestEntity.setStatus(RequestStatusEnum.REFUSED);
        this.requestRepository.save(requestEntity);
    }
}
