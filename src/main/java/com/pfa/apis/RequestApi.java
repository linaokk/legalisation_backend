package com.pfa.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("requests")
@Secured("ROLE_USER")
public interface RequestApi {

    @GetMapping(value= "find")
    public ResponseEntity<?> find();

}
