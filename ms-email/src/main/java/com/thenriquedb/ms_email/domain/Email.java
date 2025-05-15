package com.thenriquedb.ms_email.domain;

import com.thenriquedb.ms_email.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "emails")
@Data
public class Email implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID userId;
    private String emailFrom;
    private String emailTo;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
}
