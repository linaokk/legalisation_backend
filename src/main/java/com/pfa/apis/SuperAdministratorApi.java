package com.pfa.apis;

import com.pfa.dtos.UserDTO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("super_admin")
@Secured("ROLE_SUPER_ADMIN")
public interface SuperAdministratorApi {

    @GetMapping("fetch_admins")
    public List<UserDTO> fetchAdministrators();

}
