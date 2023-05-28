package com.pfa.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("fetch_p_requests")
    public ResponseEntity<?> fetchPendingRequests();

    @PutMapping("request/{requestId}/validate")
    public ResponseEntity<?> validateRequest(@RequestParam("document") MultipartFile document, @PathVariable("requestId") Long requestId );


    @PutMapping("request/{requestId}/refuse")
    public ResponseEntity<?> refuseRequest(@PathVariable("requestId") Long requestId );

}
