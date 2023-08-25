package com.mybank.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mybank.entities.Seller;
import com.mybank.repositories.SellerRepository;
import com.mybank.exceptions.NotFoundException;

@Service
public class SellerService {

	@Autowired
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository salesPersonRepository) {
        this.sellerRepository = salesPersonRepository;
    }

    public Seller createSeller(Seller salesPerson) {
        return sellerRepository.save(salesPerson);
    }

    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Seller not found with id: " + id));
    }

    // Other service methods
}