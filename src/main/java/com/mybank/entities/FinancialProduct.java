package com.mybank.entities;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "FinancialProducts")
public class FinancialProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kind_id", nullable = false) // NOTE: Careful here
    private FinancialProductKind kind;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FinancialProductKind getKind() {
		return kind;
	}

	public void setKind(FinancialProductKind kind) {
		this.kind = kind;
	}

//	public double getPointsPerAmount() {
//		return pointsPerAmount;
//	}
//
//	public void setPointsPerAmount(double pointsPerAmount) {
//		this.pointsPerAmount = pointsPerAmount;
//	}

    
}
