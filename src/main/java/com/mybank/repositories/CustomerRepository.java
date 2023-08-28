package com.mybank.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybank.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
