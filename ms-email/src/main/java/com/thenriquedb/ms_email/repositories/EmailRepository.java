package com.thenriquedb.ms_email.repositories;

import com.thenriquedb.ms_email.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
