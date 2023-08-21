package com.mybank.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.mybank.entities.SalesPerson;
import com.mybank.repositories.SalesPersonRepository;
import com.mybank.exceptions.NotFoundException;

@Service
public class SalesPersonService {

    private final SalesPersonRepository salesPersonRepository;

    @Autowired
    public SalesPersonService(SalesPersonRepository salesPersonRepository) {
        this.salesPersonRepository = salesPersonRepository;
    }

    public SalesPerson createSalesPerson(SalesPerson salesPerson) {
        return salesPersonRepository.save(salesPerson);
    }

    public SalesPerson getSalesPersonById(Long id) {
        return salesPersonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SalesPerson not found with id: " + id));
    }

    // Other service methods
}