package com.pfa.entities;

import com.pfa.dtos.SignupDTO;
import lombok.*;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "client",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"numeroIdentite"}))
public class ClientEntity extends UserEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PieceDidentiteEnum pieceDidentite;

    @Column
    private String numeroIdentite;

    @Column
    private String prenom;

    @Column
    private String nom;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SexeEnum sexe;

    @Column
    private Date dateNaissance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NationaliteEnum nationalite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituationFamilialeEnum situationFamilialeEnum;

    @Column
    private String email;

    @Column
    private String tele;

    @Column
    private String adresseDeResidence;

    @Column
    private String nomPere;

    @Column
    private String nomMere;

    @Column(nullable = false, name = "client_active")
    private boolean c_active;


    public ClientEntity() {
        super(null, null, null);
    }

    @Builder
    public ClientEntity(Long id, String login, String password, PieceDidentiteEnum pieceDidentite, String numeroIdentite, String prenom, String nom, SexeEnum sexe, Date dateNaissance, NationaliteEnum nationalite, SituationFamilialeEnum situationFamilialeEnum, String email, String tele, String adresseDeResidence, String nomPere, String nomMere, boolean c_active) {
        super(id, login, password);
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

    public static ClientEntity from(SignupDTO signupDTO) {
        return ClientEntity.builder()
                .id(null)
                .login(signupDTO.getNumIdentite())
                .password(signupDTO.getPassword())
                .pieceDidentite(PieceDidentiteEnum.valueOf(signupDTO.getPieceDidentite()))
                .numeroIdentite(signupDTO.getNumIdentite())
                .prenom(signupDTO.getPrenom())
                .nom(signupDTO.getNom())
                .sexe(SexeEnum.valueOf(signupDTO.getSexe()))
                .dateNaissance(signupDTO.getDateNaissance())
                .nationalite(NationaliteEnum.valueOf(signupDTO.getNationalite()))
                .situationFamilialeEnum(SituationFamilialeEnum.valueOf(signupDTO.getSituationFam()))
                .email(signupDTO.getEmail())
                .tele(signupDTO.getNumTele())
                .adresseDeResidence(signupDTO.getAdresseResidence())
                .nomPere(signupDTO.getNomPere())
                .nomMere(signupDTO.getNomMere())
                .build();
    }
}