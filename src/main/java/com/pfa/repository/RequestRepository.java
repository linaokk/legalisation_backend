package com.pfa.repository;

import com.pfa.entities.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {


    @Query("FROM RequestEntity re JOIN re.userEntity ur WHERE ur.identityCode = ?1 ")
    List<RequestEntity> findByIdentityCode(String identityCode);
}
