package com.mybank.entities;


import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employees")
@PrimaryKeyJoinColumn(name = "id")
public class Employee extends Person {
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;
    
    @OneToMany(mappedBy = "manager")
    private List<Employee> managedEmployees = new ArrayList<>(); // Employees managed by this manager
    
    
    @OneToMany(mappedBy = "seller")
    private List<Sale> sales = new ArrayList<>(); // Employees managed by this manager

    public int addManagedEmployee(Employee employee) {
        if (this.role.getName().equals("MANAGER")) { // Check if this employee is a manager
            managedEmployees.add(employee);
            employee.setManager(this); // Set the manager for the added employee
            return 0;
        } else {
        	return 1;
        }
    }

    public int removeManagedEmployee(Employee employee) {
        if (this.role.getName().equals("MANAGER") && managedEmployees.contains(employee)) {
            managedEmployees.remove(employee);
            employee.setManager(null); // Unset the manager for the removed employee
            return 0;
        } else {
        	return 1;
        }
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}



}
