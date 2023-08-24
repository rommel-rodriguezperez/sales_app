package com.mybank.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {

}
