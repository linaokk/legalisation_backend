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


@Getter
@Setter
@Entity
@Table(name = "clients",
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


    public ClientEntity() {
        super(null, null, null, false, null, null);
    }

    @Builder
    public ClientEntity(Long id, String login, String password, PieceDidentiteEnum pieceDidentite, String numeroIdentite, String prenom, String nom, SexeEnum sexe, Date dateNaissance, NationaliteEnum nationalite, SituationFamilialeEnum situationFamilialeEnum, String email, String tele, String adresseDeResidence, String nomPere, String nomMere, boolean c_active, String userPicture, Collection<RoleEnum> roleEnums) {
        super(id, login, password, c_active, roleEnums, userPicture);
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
    }

    public static ClientEntity from(SignupDTO signupDTO, PasswordEncoder passwordEncoder) {
        return ClientEntity.builder()
                .id(null)
                .login(signupDTO.getIdentityCode())
                .password(passwordEncoder.encode(signupDTO.getPassword()))
                .pieceDidentite(PieceDidentiteEnum.valueOf(signupDTO.getIdentityType()))
                .numeroIdentite(signupDTO.getIdentityCode())
                .prenom(signupDTO.getFirstname())
                .nom(signupDTO.getLastname())
                .sexe(SexeEnum.valueOf(signupDTO.getSexe()))
                .dateNaissance(signupDTO.getBirthday())
                .nationalite(NationaliteEnum.valueOf(signupDTO.getNationality()))
                .situationFamilialeEnum(SituationFamilialeEnum.valueOf(signupDTO.getFamilySituation()))
                .email(signupDTO.getEmail())
                .tele(signupDTO.getPhoneNumber())
                .adresseDeResidence(signupDTO.getAddress())
                .nomPere(signupDTO.getFatherName())
                .nomMere(signupDTO.getMotherName())
                .c_active(Boolean.FALSE)
                .roleEnums(Arrays.asList(RoleEnum.ROLE_USER))
                .build();
    }
}