package com.pfa.apis;

import com.pfa.dtos.RequestDTO;
import com.pfa.dtos.SignupAdminRequestDTO;
import com.pfa.dtos.SignupDTO;
import com.pfa.dtos.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("super_admin")
@Secured("ROLE_SUPER_ADMIN")
public interface SuperAdministratorApi {

    @GetMapping("fetch_admins")
    public List<UserDTO> fetchAdministrators();

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("enable_admin/{identityCode}")
    public void enableAdministrator(@PathVariable String identityCode);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("disable_admin/{identityCode}")
    public void disableAdministrator(@PathVariable String identityCode);


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("add_admin")
    public void addAdministrator(@RequestBody SignupAdminRequestDTO requestDTO);

}
