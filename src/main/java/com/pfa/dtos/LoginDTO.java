package com.pfa.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    String numeroDidentite;
    String password;
    String userPicture;

}
