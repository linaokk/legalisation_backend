package com.pfa.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateMyAccountDTO {

    private String email;
    private String phoneNumber;
    private String password;

}
