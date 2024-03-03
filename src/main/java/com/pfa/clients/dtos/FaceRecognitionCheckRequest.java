package com.pfa.clients.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class FaceRecognitionCheckRequest {

    private String face1;
    private String face2;

    @Builder(access = AccessLevel.PRIVATE)
    public FaceRecognitionCheckRequest(String face1, String face2) {
        this.face1 = face1;
        this.face2 = face2;
    }

    public static FaceRecognitionCheckRequest from(String face1, String face2) {
        return FaceRecognitionCheckRequest.builder()
                .face1(face1)
                .face2(face2)
                .build();
    }
}
