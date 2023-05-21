package com.pfa.services;

import com.pfa.entities.RequestEntity;
import com.pfa.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;


    public List<RequestEntity> findAllByIdentityCode(String identityCode) {
        return this.requestRepository.findByIdentityCode(identityCode);
    }
}
