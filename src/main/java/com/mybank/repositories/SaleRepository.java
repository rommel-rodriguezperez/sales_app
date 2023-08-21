package com.mybank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
