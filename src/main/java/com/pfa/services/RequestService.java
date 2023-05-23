package com.pfa.services;

import com.pfa.entities.ClientEntity;
import com.pfa.entities.RequestEntity;
import com.pfa.entities.TypeDocumentEnum;
import com.pfa.entities.UserEntity;
import com.pfa.repository.ClientRepository;
import com.pfa.repository.RequestRepository;
import com.pfa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;


    public List<RequestEntity> findAllByIdentityCode(String identityCode) {
        return this.requestRepository.findByIdentityCode(identityCode);
    }

    public void add(String identityCode, MultipartFile document, String description, String documentType) throws IOException {
        UserEntity userEntity = this.userRepository.findByIdentityCode(identityCode)
                .orElseThrow(() -> new RuntimeException("Client not found !"));
        byte[] byteArr = document.getResource().getContentAsByteArray();
        RequestEntity requestEntity = RequestEntity.builder()
                .documentType(TypeDocumentEnum.valueOf(documentType))
                .validated(Boolean.FALSE)
                .insertedAt(new Date(System.currentTimeMillis()))
                .description(description)
                .document(byteArr)
                .userEntity(userEntity)
                .build();
        this.requestRepository.save(requestEntity);
    }
}
