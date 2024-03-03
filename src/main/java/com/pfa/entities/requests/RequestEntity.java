package com.pfa.entities.requests;

import com.pfa.entities.users.enums.TypeDocumentEnum;
import com.pfa.entities.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeDocumentEnum documentType;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private byte[] document;

    @Column(name="user_picture", nullable = false, length = Integer.MAX_VALUE)
    private String userPicture;

    @Column(name= "inserted_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime insertedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatusEnum status;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity userEntity;

}
