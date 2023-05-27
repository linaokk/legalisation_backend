package com.pfa.repository;

import com.pfa.entities.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findByIdentityCode(String identityCode);

    @Query("FROM UserEntity ce where ce.active <> true")
    List<UserEntity> fetchDisabledClients();

}
