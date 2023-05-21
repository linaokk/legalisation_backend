package com.pfa.dtos;

import com.pfa.entities.ClientEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ClientDTO {

    private String username;
    private String firstname;
    private String lastname;
    private String sexe;
    private String familialSituation;
    private String phoneNumber;
    private String identityCode;
    private String identityType;
    private String email;
    private boolean active;
    private List<String> authorities;

    public static ClientDTO from(ClientEntity clientEntity) {
        List<String> userAuthorities = clientEntity.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ClientDTO.builder()
                .username(clientEntity.getUsername())
                .firstname(clientEntity.getFirstname())
                .lastname(clientEntity.getLastname())
                .sexe(clientEntity.getSexe().toString())
                .email(clientEntity.getEmail())
                .familialSituation(clientEntity.getFamilialSituationEnum().toString())
                .phoneNumber(clientEntity.getPhoneNumber())
                .identityCode(clientEntity.getIdentityCode())
                .identityType(clientEntity.getIdentityType().toString())
                .active(clientEntity.isEnabled())
                .authorities(userAuthorities)
                .build();
    }
}
