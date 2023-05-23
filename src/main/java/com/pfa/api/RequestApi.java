package com.pfa.api;

import com.pfa.dtos.RequestFormDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RequestMapping("requests")
@Secured("ROLE_USER")
public interface RequestApi {

    @GetMapping(value= "find")
    public ResponseEntity<?> find();

}
