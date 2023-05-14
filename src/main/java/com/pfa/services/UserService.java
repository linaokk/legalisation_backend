package com.pfa.services;

import com.pfa.dtos.LoginDTO;
import com.pfa.dtos.SignupDTO;
import com.pfa.entities.ClientEntity;
import com.pfa.exceptions.UserNotFoundException;
import com.pfa.exceptions.UsernameAlreadyTakenException;
import com.pfa.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ClientRepository clientRepository;


    public boolean checkLoginAvailability(String login) {
        return !clientRepository.findByLogin(login).isPresent();
    }


    public ClientEntity signup(SignupDTO signupDTO) throws UsernameAlreadyTakenException {
        boolean loginAvailability = this.checkLoginAvailability(signupDTO.getNumIdentite());
        if (!loginAvailability) {
            throw new UsernameAlreadyTakenException();
        }

        ClientEntity clientEntity = ClientEntity.from(signupDTO);
        return clientRepository.save(clientEntity);

    }


    public ClientEntity login(LoginDTO loginDTO) throws UserNotFoundException {
        Optional<ClientEntity> optClient = this.clientRepository.findByNumeroIdentiteAndPassword(loginDTO.getLogin(), loginDTO.getPassword());
        ClientEntity clientEntity = optClient.orElseThrow(() -> new RuntimeException("le client nexiste pas"));
        return clientEntity;
    }


}
