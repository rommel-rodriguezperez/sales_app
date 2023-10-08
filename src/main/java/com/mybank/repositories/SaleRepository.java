package com.mybank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.entities.Sale;
import com.mybank.entities.Seller;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	 List<Sale> findAllBySeller(Seller seller);
}
