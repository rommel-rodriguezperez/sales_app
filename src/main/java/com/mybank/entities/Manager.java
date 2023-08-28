package com.mybank.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends Employee {

    @JsonManagedReference
    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    private List<Employee> managedEmployees = new ArrayList<>(); // Employees managed by this manager

    public int addManagedEmployee(Employee employee) {
        if (this.getRole().getName().equals("MANAGER")) { // Check if this employee is a manager
            managedEmployees.add(employee);
            employee.setManager(this); // Set the manager for the added employee
            return 0;
        } else {
        	return 1;
        }
    }

    public int removeManagedEmployee(Employee employee) {
        if (this.getRole().getName().equals("MANAGER") && managedEmployees.contains(employee)) {
            managedEmployees.remove(employee);
            employee.setManager(null); // Unset the manager for the removed employee
            return 0;
        } else {
        	return 1;
        }
    }

    // TODO: Careful, might cause trouble
    public List<Employee> getManagedEmployees() {
    	return this.managedEmployees;
    }
}
