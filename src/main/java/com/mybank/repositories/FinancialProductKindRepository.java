package com.mybank.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.entities.FinancialProductKind;


public interface FinancialProductKindRepository extends JpaRepository<FinancialProductKind, Long> {

	  List<FinancialProductKind> findAll();
	  
	  FinancialProductKind findById(long id);

}
