package com.mybank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.entities.SaleDetail;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
}
