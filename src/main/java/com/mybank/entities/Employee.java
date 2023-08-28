package com.mybank.entities;


import java.util.List;


import java.util.ArrayList;

import org.hibernate.annotations.DiscriminatorFormula;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Table(name = "Employees")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula(
	    "(select r.name from Roles r where r.id = role_id)"
	)
public class Employee {

    @Id
    private Long id;
	
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @MapsId
    private Person person;	
	
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
    

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}



}
