package com.pfa.repository;

import com.pfa.entities.requests.RequestEntity;
import com.pfa.entities.requests.RequestStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {


    @Query("FROM RequestEntity re JOIN re.userEntity ur WHERE ur.identityCode = ?1 ")
    List<RequestEntity> findByIdentityCode(String identityCode);


    @Query("FROM RequestEntity re where re.status = ?1 ")
    List<RequestEntity> findByStatus(RequestStatusEnum requestStatusEnum);
}
