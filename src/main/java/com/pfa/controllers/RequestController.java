package com.pfa.controllers;


import com.pfa.apis.RequestApi;
import com.pfa.dtos.RequestDTO;
import com.pfa.entities.RequestEntity;
import com.pfa.services.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RequestController implements RequestApi {

    private final RequestService requestService;

    @Override
    public ResponseEntity<?> find() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        String identityCode = principal.getUsername();
        List<RequestEntity> requestEntities = this.requestService.findAllByIdentityCode(identityCode);
        List<RequestDTO> requestDTOs = requestEntities.stream().map(RequestDTO::from).collect(Collectors.toList());
        return ResponseEntity.ok(requestDTOs);
    }

    @PostMapping(path = "add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> add(@RequestParam("document") MultipartFile document, @RequestParam("description") String description, @RequestParam("documentType") String documentType) throws IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        String identityCode = principal.getUsername();

        this.requestService.add(identityCode, document, description, documentType);
        return ResponseEntity.ok(null);
    }

}
