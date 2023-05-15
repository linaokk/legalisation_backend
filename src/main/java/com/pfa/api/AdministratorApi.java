package com.pfa.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public interface AdministratorApi {

    @GetMapping("admin/fetch_user")
    public ResponseEntity<?> fetchUser();

    @PutMapping("admin/enable_user/{login}")
    public ResponseEntity<?> enableUser(@PathVariable String login);

    @PutMapping("admin/disable_user/{login}")
    public ResponseEntity<?> disableUser(@PathVariable String login);


}
