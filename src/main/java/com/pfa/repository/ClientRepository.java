package com.pfa.repository;

import com.pfa.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByLogin(String login);

    Optional<ClientEntity> findByNumeroIdentiteAndPassword(String numeroDidentite, String password);
}
