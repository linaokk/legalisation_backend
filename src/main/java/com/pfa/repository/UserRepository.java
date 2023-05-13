package com.pfa.repository;

import com.pfa.entities.Client;
import com.pfa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Client> findByLogin(String login);
}
