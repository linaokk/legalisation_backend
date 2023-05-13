package com.pfa.services;

import com.pfa.dtos.LoginDTO;
import com.pfa.dtos.SignupDTO;
import com.pfa.entities.*;
import com.pfa.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ClientRepository clientRepository;


    public boolean checkLoginAvailability(String login ){

        return clientRepository.findByLogin(login).isPresent();
    }


    public Client signup(SignupDTO signupDTO) {
        Client user = new Client(
                signupDTO.getPassword(),
                PieceDidentiteEnum.valueOf(signupDTO.getPieceDidentite()),
                signupDTO.getNumIdentite(),
                signupDTO.getPrenom(),
                signupDTO.getNom(),
                SexeEnum.valueOf(signupDTO.getSexe()),
                signupDTO.getDateNaissance(),
                NationaliteEnum.valueOf(signupDTO.getNationalite()),
                SituationFamilialeEnum.valueOf(signupDTO.getSituationFam()),
                signupDTO.getEmail(),
                signupDTO.getNumTele(),
                signupDTO.getAdresseResidence(),
                signupDTO.getNomPere(),
                signupDTO.getNomMere(),
                false
        ) ;

       return clientRepository.save(user);

    }


    public Client login(LoginDTO loginDTO) {
        Optional<Client> optClient =this.clientRepository.findByNumeroIdentiteAndPassword(loginDTO.getNumeroDidentite(),loginDTO.getPassword());
        Client client = optClient.orElseThrow(() -> new RuntimeException("le client nexiste pas"));
        return client;
    }


}
