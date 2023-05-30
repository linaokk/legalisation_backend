package com.pfa.services;

import com.pfa.entities.requests.RequestEntity;
import com.pfa.entities.requests.RequestStatusEnum;
import com.pfa.entities.users.enums.TypeDocumentEnum;
import com.pfa.entities.users.UserEntity;
import com.pfa.repository.RequestRepository;
import com.pfa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;


    public List<RequestEntity> findAllByIdentityCode(String identityCode) {
        return this.requestRepository.findByIdentityCode(identityCode);
    }

    public void add(String identityCode, MultipartFile document, String description, String documentType, String userPicture) throws IOException {
        UserEntity userEntity = this.userRepository.findByIdentityCode(identityCode)
                .orElseThrow(() -> new RuntimeException("Client not found !"));
        byte[] byteArr = document.getResource().getContentAsByteArray();

        RequestEntity requestEntity = RequestEntity.builder()
                .documentType(TypeDocumentEnum.valueOf(documentType))
                .status(RequestStatusEnum.INITIAL_REQUEST)
                .insertedAt(LocalDateTime.now())
                .description(description)
                .document(byteArr)
                .userPicture(userPicture)
                .userEntity(userEntity)
                .build();

        this.requestRepository.save(requestEntity);
    }
}
