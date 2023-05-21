package com.pfa.repository;

import com.pfa.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByLogin(String login);

    @Query("FROM ClientEntity ce where ce.active <> true")
    List<ClientEntity> fetchDisabledClients();

}
