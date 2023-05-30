package com.pfa.dtos;

import com.pfa.entities.requests.RequestEntity;
import com.pfa.entities.requests.RequestStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private Long id;

    private String documentType;

    private LocalDateTime insertedAt;

    private String status;

    private String description;

    private String userPicture;

    private byte[] document;

    private UserDTO user;

    public static RequestDTO from(RequestEntity requestEntity) {
        return RequestDTO.builder()
                .id(requestEntity.getId())
                .documentType(requestEntity.getDocumentType().name())
                .insertedAt(requestEntity.getInsertedAt())
                .status(requestEntity.getStatus().name())
                .document(requestEntity.getDocument())
                .description(requestEntity.getDescription())
                .userPicture(requestEntity.getUserPicture())
                .user(UserDTO.from(requestEntity.getUserEntity()))
                .build();
    }

}
