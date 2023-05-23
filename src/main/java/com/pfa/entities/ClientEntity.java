package com.pfa.entities;

import com.pfa.dtos.SignupDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "clients")
public class ClientEntity extends UserEntity {

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SexeEnum sexe;

    @Column(name="birth_day")
    private Date birthday;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NationaliteEnum nationality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituationFamilialeEnum familialSituationEnum;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String fatherName;

    @Column
    private String motherName;

    public ClientEntity() {
        super(null, null, null, false, null, null, null, null, null, null);
    }

    @Builder
    public ClientEntity(Long id, String login, String password, IdentityTypeEnum identityType, String identityCode, String firstname, String lastname, SexeEnum sexe, Date birthday, NationaliteEnum nationality, SituationFamilialeEnum familialSituationEnum, String email, String phoneNumber, String address, String fatherName, String motherName, boolean c_active, String userPicture, Collection<RoleEnum> roleEnums, String signature, List<RequestEntity> requestEntities) {
        super(id, login, password, c_active, roleEnums, userPicture, signature, requestEntities, identityType, identityCode);
        this.firstname = firstname;
        this.lastname = lastname;
        this.sexe = sexe;
        this.birthday = birthday;
        this.nationality = nationality;
        this.familialSituationEnum = familialSituationEnum;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

    public static ClientEntity from(SignupDTO signupDTO, PasswordEncoder passwordEncoder) {
        return ClientEntity.builder()
                .id(null)
                .login(signupDTO.getIdentityCode())
                .password(passwordEncoder.encode(signupDTO.getPassword()))
                .identityType(IdentityTypeEnum.valueOf(signupDTO.getIdentityType()))
                .identityCode(signupDTO.getIdentityCode())
                .firstname(signupDTO.getFirstname())
                .lastname(signupDTO.getLastname())
                .sexe(SexeEnum.valueOf(signupDTO.getSexe()))
                .birthday(signupDTO.getBirthday())
                .nationality(NationaliteEnum.valueOf(signupDTO.getNationality()))
                .familialSituationEnum(SituationFamilialeEnum.valueOf(signupDTO.getFamilySituation()))
                .email(signupDTO.getEmail())
                .phoneNumber(signupDTO.getPhoneNumber())
                .address(signupDTO.getAddress())
                .fatherName(signupDTO.getFatherName())
                .motherName(signupDTO.getMotherName())
                .c_active(Boolean.FALSE)
                .roleEnums(Arrays.asList(RoleEnum.ROLE_USER))
                .signature(signupDTO.getSignature())
                .userPicture(signupDTO.getUserPicture())
                .build();
    }
}