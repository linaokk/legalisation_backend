package com.pfa.clients;

import com.pfa.clients.dtos.FaceRecognitionCheckRequest;
import com.pfa.clients.dtos.FaceRecognitionResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "faceRecognitionClient", url = "${microservice-common.url}")
public interface FaceRecognitionClient {


    @PostMapping("/api/face_recognition/check")
    FaceRecognitionResult checkSameFace(@RequestBody FaceRecognitionCheckRequest faceRecognitionCheckRequest);

}
