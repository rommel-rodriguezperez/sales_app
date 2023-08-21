package com.mybank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mybank.entities.Customer;
import com.mybank.services.CustomerService;

import org.springframework.validation.annotation.Validated;



@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:8080")
public class CustomerController {

	// TODO: Better to use DTOs for all of the controller, in other words
	// make all the services return DTO instead of raw entities. The current
	// approach might leak important information without anyone noticing.
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

}
