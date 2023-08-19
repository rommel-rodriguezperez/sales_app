package com.mybank.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybank.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional custom queries if needed
}
