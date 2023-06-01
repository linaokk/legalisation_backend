package com.pfa.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupAdminRequestDTO {

    private String password;

    private String identityType;

    private String identityCode;

    private String firstname;

    private String lastname;

    private String sexe;

    private Date birthday;

    private String nationality;

    private String familySituation;

    private String email;

    private String phoneNumber;

    private String address;

    private String fatherName;

    private String motherName;

}
