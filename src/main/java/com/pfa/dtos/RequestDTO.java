package com.pfa.dtos;

import com.pfa.entities.RequestEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private Long id;

    private String documentType;

    private Date insertedAt;

    private boolean validated;

    private byte[] document;

    public static RequestDTO from(RequestEntity requestEntity) {
        return RequestDTO.builder()
                .id(requestEntity.getId())
                .documentType(requestEntity.getDocumentType().name())
                .insertedAt(requestEntity.getInsertedAt())
                .validated(requestEntity.isValidated())
                .document(requestEntity.getDocument())
                .build();
    }

}
