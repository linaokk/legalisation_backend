package com.pfa.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@RequestMapping("requests")
@Secured("ROLE_USER")
public interface RequestApi {

    @GetMapping("find")
    public ResponseEntity<?> find();

}
