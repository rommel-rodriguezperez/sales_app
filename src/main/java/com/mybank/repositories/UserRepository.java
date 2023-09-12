package com.mybank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.entities.ApplicationUser;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);
}

