package com.pfa.dtos;

import lombok.*;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {


    private String password;
    private String pieceDidentite;

    private String numIdentite;

    private String prenom;

    private String nom;

    private String sexe;

    private Date dateNaissance;

    private String nationalite;

    private String situationFam;

    private String email;

    private String numTele;

    private String adresseResidence;

    private String nomPere;

    private String nomMere;

}
