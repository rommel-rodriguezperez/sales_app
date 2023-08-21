package com.mybank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.entities.FinancialProduct;

public interface FinancialProductRepository extends JpaRepository<FinancialProduct, Long> {
}
