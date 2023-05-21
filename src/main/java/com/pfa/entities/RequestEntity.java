package com.pfa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Entity
@Table(name = "requests")
@Getter
@Setter
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeDocumentEnum documentType;

    @Column(nullable = false)
    private byte[] document;

    @Column(name= "inserted_at", nullable = false)
    private Date insertedAt;

    @Column(nullable = false)
    private boolean validated;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity userEntity;

}
