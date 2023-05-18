package com.pfa.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Secured("ROLE_ADMIN") // @PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("admin")
public interface AdministratorApi {

    @GetMapping("fetch_users")
    public ResponseEntity<?> fetchUsers();

    @PutMapping("enable_user/{login}")
    public ResponseEntity<?> enableUser(@PathVariable String login);

    @PutMapping("disable_user/{login}")
    public ResponseEntity<?> disableUser(@PathVariable String login);

    @GetMapping("fetch_d_clients")
    public ResponseEntity<?> fetchDisabledClients();

}
