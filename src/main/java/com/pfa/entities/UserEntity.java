package com.pfa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Blob;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@AllArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @Setter
    @Column(nullable = false, name = "client_active")
    private boolean c_active;

    @ElementCollection(targetClass = RoleEnum.class)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    Collection<RoleEnum> roles;

    @Lob
    @Setter
    @Column(name = "user_picture")
    private String userPicture;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role -> (GrantedAuthority) role::name)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.c_active;
    }
}


