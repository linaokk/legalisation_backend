package com.pfa.dtos;

import com.pfa.entities.NationaliteEnum;
import com.pfa.entities.PieceDidentiteEnum;
import com.pfa.entities.SexeEnum;
import com.pfa.entities.SituationFamilialeEnum;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Builder
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

    private String  situationFam;

    private String email;

    private String numTele;

    private String adresseResidence;

    private String nomPere;

    private String nomMere;

}
