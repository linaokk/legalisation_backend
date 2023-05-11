package com.pfa.dtos;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {

    private String login;
    private String password;
    private String firstName;
    private String lastName;

}
