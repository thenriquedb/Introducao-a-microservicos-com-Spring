package com.thenriquedb.ms_users.repositories;

import com.thenriquedb.ms_users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
