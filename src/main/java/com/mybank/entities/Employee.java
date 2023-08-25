package com.mybank.entities;


import java.util.List;


import java.util.ArrayList;

import org.hibernate.annotations.DiscriminatorFormula;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Table(name = "Employees")
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula(
	    "(select r.name from Roles r where r.id = role_id)"
	)
public class Employee extends Person {
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
    
    
    @OneToMany(mappedBy = "seller")
    private List<Sale> sales = new ArrayList<>(); // Employees managed by this manager


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}



}
