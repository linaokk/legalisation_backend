package com.pfa.config;

import com.pfa.services.AdminService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OnStartUpConfig {

    private final AdminService adminService;

    @PostConstruct
    public void postConstruct() {
        adminService.createDefaultAdministrator();
        adminService.createDefaultSuperAdministrator();
    }

}
