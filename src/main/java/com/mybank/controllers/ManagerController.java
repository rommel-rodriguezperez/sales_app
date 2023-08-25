package com.mybank.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mybank.entities.Manager;
import com.mybank.services.ManagerService;


@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:8080")
public class ManagerController {

	// TODO: Better to use DTOs for all of the controller, in other words
	// make all the services return DTO instead of raw entities. The current
	// approach might leak important information without anyone noticing.
    private final ManagerService managerService;

    public ManagerController(ManagerService financialProductService) {
        this.managerService = financialProductService;
    }

//    @PostMapping
//    public ResponseEntity<Manager> createManager(@RequestBody Manager financialProduct) {
//        Manager createdManager = financialProductService.createManager(financialProduct);
//        return ResponseEntity.ok(createdManager);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getManagerById(@PathVariable Long id) {
        Optional<Manager> managerOptional = managerService
        		.getManagerById(id);

        if (managerOptional.isPresent()) {
            return ResponseEntity.ok(managerOptional.get());
        } else {
            // Return a 404 Not Found response with a custom error message
            return ResponseEntity
            		.status(HttpStatus.NOT_FOUND)
                    .body("Manager with ID " + id + " not found");
        }
    }

}
