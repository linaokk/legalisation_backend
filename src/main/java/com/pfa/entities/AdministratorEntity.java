package com.pfa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "administrators")
public class AdministratorEntity extends UserEntity {
    public AdministratorEntity() {
        super(null, null, null, false, null, null);
    }

    @Builder
    public AdministratorEntity(Long id, String login, String password, boolean c_active, Collection<RoleEnum> roles, String userPicture) {
        super(id, login, password, c_active, roles, userPicture);
    }

    public static AdministratorEntity getDefault(PasswordEncoder passwordEncoder) {
        return AdministratorEntity.builder()
                .id(null)
                .login("admin")
                .password(passwordEncoder.encode("admin"))
                .c_active(Boolean.TRUE)
                .roles(Arrays.asList(RoleEnum.ROLE_USER, RoleEnum.ROLE_ADMIN))
                .build();
    }
}
