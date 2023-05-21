package com.pfa.services;

import com.pfa.entities.AdministratorEntity;
import com.pfa.entities.ClientEntity;
import com.pfa.exceptions.UserNotFoundException;
import com.pfa.repository.ClientRepository;
import com.pfa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoded;

    public List<ClientEntity> fetchUsers() {
        return clientRepository.findAll();
    }

    public void enableClient(String login) throws UserNotFoundException {
        ClientEntity clientEntity = clientRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException());
        clientEntity.setActive(true);
        clientRepository.save(clientEntity);
    }

    public void disableClient(String login) throws UserNotFoundException {
        ClientEntity clientEntity = clientRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException());
        clientEntity.setActive(false);
        clientRepository.save(clientEntity);
    }

    public List<ClientEntity> fetchDisabledClients() {
        return clientRepository.fetchDisabledClients();
    }

    public void createDefaultAdministrator() {
        AdministratorEntity administratorEntity = AdministratorEntity.getDefault(passwordEncoded);
        String username = administratorEntity.getUsername();
        boolean exists = this.userRepository.findByLogin(username).isPresent();
        if (exists) return;
        this.userRepository.save(administratorEntity);
    }
}
