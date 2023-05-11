package com.pfa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name = "client",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"numeroIdentite"}))

public class Client extends User {
    @Column(nullable = false)
    private PieceDidentiteEnum pieceDidentite;

    @Column(nullable = false)
    private String numeroIdentite;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private SexeEnum sexe;

    @Column(nullable = false)
    private Date dateNaissance;

    @Column(nullable = false)
    private NationaliteEnum nationalite;

    @Column(nullable = false)
    private SituationFamilialeEnum situationFamilialeEnum;

    @Column(nullable = false)
    private String email;


    @Column(name = "numero_telephone", nullable = false)
    private String tele;

    @Column(nullable = false)
    private String adresseDeResidence;

    @Column(nullable = false)
    private String nomPere;

    @Column(nullable = false)
    private String nomMere;


    @Column(nullable = false, name = "client_active")
    private boolean c_active;


    public Client(String login, String password, String numeroIdentite, String prenom, String nom,
                  SexeEnum sexe, Date dateNaissance, NationaliteEnum nationalite,
                  SituationFamilialeEnum situationFamilialeEnum, String email, PieceDidentiteEnum pieceDidentite,
                  String adresseDeResidence, String nomPere, String nomMere
    ) {
        super(login, password);
        this.numeroIdentite = numeroIdentite;
        this.prenom = prenom;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.nationalite = nationalite;
        this.situationFamilialeEnum = situationFamilialeEnum;
        this.email = email;
        this.pieceDidentite = pieceDidentite;
        this.adresseDeResidence = adresseDeResidence;
        this.nomPere = nomPere;
        this.nomMere = nomMere;
        this.c_active = false;
    }

}