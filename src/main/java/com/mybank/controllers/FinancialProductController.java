package com.mybank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mybank.entities.FinancialProduct;
import com.mybank.services.FinancialProductService;


@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:8080")
public class FinancialProductController {

	// TODO: Better to use DTOs for all of the controller, in other words
	// make all the services return DTO instead of raw entities. The current
	// approach might leak important information without anyone noticing.
    private final FinancialProductService financialProductService;

    @Autowired
    public FinancialProductController(FinancialProductService financialProductService) {
        this.financialProductService = financialProductService;
    }

    @PostMapping
    public ResponseEntity<FinancialProduct> createFinancialProduct(@RequestBody FinancialProduct financialProduct) {
        FinancialProduct createdFinancialProduct = financialProductService.createFinancialProduct(financialProduct);
        return ResponseEntity.ok(createdFinancialProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialProduct> getFinancialProductById(@PathVariable Long id) {
        FinancialProduct financialProduct = financialProductService.getFinancialProductById(id);
        return ResponseEntity.ok(financialProduct);
    }

}
