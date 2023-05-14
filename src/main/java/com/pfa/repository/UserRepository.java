package com.pfa.repository;

import com.pfa.entities.ClientEntity;
import com.pfa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<ClientEntity> findByLogin(String login);

}
