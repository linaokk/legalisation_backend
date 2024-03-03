package com.pfa.apis;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RequestMapping("requests")
@Secured("ROLE_USER")
public interface RequestApi {

    @GetMapping(value = "find")
    public ResponseEntity<?> find();

    @PostMapping(path = "add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> add(@RequestParam("document") MultipartFile document, @RequestParam("description") String description, @RequestParam("documentType") String documentType, @RequestParam("userPicture") String userPicture) throws IOException;

}