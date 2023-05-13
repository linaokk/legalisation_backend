package com.pfa.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name = "client",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"numeroIdentite"}))

public class Client extends User {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PieceDidentiteEnum pieceDidentite;

    @Column(nullable = false)
    private String numeroIdentite;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SexeEnum sexe;

    @Column(nullable = false)
    private Date dateNaissance;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NationaliteEnum nationalite;
    @Enumerated(EnumType.STRING)
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


    public Client(
                  String password,
                  PieceDidentiteEnum pieceDidentite,
                  String numeroIdentite,
                  String prenom,
                  String nom,
                  SexeEnum sexe,
                  Date dateNaissance,
                  NationaliteEnum nationalite,
                  SituationFamilialeEnum situationFamilialeEnum,
                  String email,
                  String tele,
                  String adresseDeResidence,
                  String nomPere,
                  String nomMere,
                  boolean c_active) {
        super(numeroIdentite, password);
        this.pieceDidentite = pieceDidentite;
        this.numeroIdentite = numeroIdentite;
        this.prenom = prenom;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.nationalite = nationalite;
        this.situationFamilialeEnum = situationFamilialeEnum;
        this.email = email;
        this.tele = tele;
        this.adresseDeResidence = adresseDeResidence;
        this.nomPere = nomPere;
        this.nomMere = nomMere;
        this.c_active = c_active;
    }
}