package com.mybank.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybank.entities.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    // Additional query methods can be defined here if needed
}

