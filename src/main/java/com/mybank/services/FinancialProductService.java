package com.mybank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.entities.FinancialProduct;
import com.mybank.repositories.FinancialProductRepository;
import com.mybank.exceptions.NotFoundException;

@Service
public class FinancialProductService {

    private final FinancialProductRepository financialProductRepository;

    @Autowired
    public FinancialProductService(FinancialProductRepository financialProductRepository) {
        this.financialProductRepository = financialProductRepository;
    }

    public FinancialProduct createFinancialProduct(FinancialProduct financialProduct) {
        return financialProductRepository.save(financialProduct);
    }

    public FinancialProduct getFinancialProductById(Long id) {
        return financialProductRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("FinancialProduct not found with id: " + id));
    }

    // Other service methods
}
