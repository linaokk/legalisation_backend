package com.pfa.services;

import com.pfa.entities.ClientEntity;
import com.pfa.exceptions.UserNotFoundException;
import com.pfa.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final ClientRepository clientRepository;

    public List<ClientEntity> fetchUsers() {
        return clientRepository.findAll();
    }

    public void enableClient(String login) throws UserNotFoundException {
        ClientEntity clientEntity = clientRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException());
        clientEntity.setC_active(true);
        clientRepository.save(clientEntity);
    }

    public void disableClient(String login) throws UserNotFoundException {
        ClientEntity clientEntity = clientRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException());
        clientEntity.setC_active(false);
        clientRepository.save(clientEntity);
    }
}
