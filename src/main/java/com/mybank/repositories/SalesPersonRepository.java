package com.mybank.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.entities.SalesPerson;

public interface SalesPersonRepository extends JpaRepository<SalesPerson, Long> {

}
