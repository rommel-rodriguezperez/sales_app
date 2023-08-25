package com.mybank.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.entities.Manager;
import com.mybank.repositories.ManagerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

	@Autowired
    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public Optional<Manager> getManagerById(Long id) {
        return managerRepository.findById(id);
    }

    public Manager saveManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }

}

