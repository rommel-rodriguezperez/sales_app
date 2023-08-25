package com.mybank.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends Employee {

    @OneToMany(mappedBy = "manager")
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
}
