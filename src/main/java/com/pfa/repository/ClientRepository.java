package com.pfa.repository;

import com.pfa.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository <Client,Long>{
    Optional<Client> findByLogin(String login);

    Optional<Client> findByNumeroIdentiteAndPassword(String numeroDidentite, String password);
}
