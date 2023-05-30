package com.pfa.dtos;

import com.pfa.entities.users.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String firstname;
    private String lastname;
    private String sexe;
    private String familialSituation;
    private String phoneNumber;
    private String identityCode;
    private String identityType;
    private String email;
    private String userPicture;
    private boolean active;
    private List<String> authorities;

    public static UserDTO from(UserEntity userEntity) {
        List<String> userAuthorities = userEntity.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return UserDTO.builder()
                .username(userEntity.getUsername())
                .firstname(userEntity.getFirstname())
                .lastname(userEntity.getLastname())
                .sexe(userEntity.getSexe().toString())
                .email(userEntity.getEmail())
                .familialSituation(userEntity.getFamilialSituationEnum().toString())
                .phoneNumber(userEntity.getPhoneNumber())
                .identityCode(userEntity.getIdentityCode())
                .identityType(userEntity.getIdentityType().toString())
                .active(userEntity.isEnabled())
                .authorities(userAuthorities)
                .userPicture(userEntity.getUserPicture())
                .build();
    }

}
