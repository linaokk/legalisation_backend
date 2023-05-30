package com.pfa.repository;

import com.pfa.entities.users.UserEntity;
import com.pfa.entities.users.enums.RoleEnum;
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

    @Query("FROM UserEntity ce JOIN ce.roles as r where r = ?1")
    List<UserEntity> findByRole(RoleEnum roleSuperAdmin);
}
