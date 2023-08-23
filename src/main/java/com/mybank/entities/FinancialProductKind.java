package com.mybank.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="financial_product_kind")
public class FinancialProductKind {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
	private FinancialProductType type;
	
    @Column(name = "points_per_amount")
    private double pointsPerAmount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FinancialProductType getType() {
		return type;
	}

	public void setType(FinancialProductType type) {
		this.type = type;
	}

	public double getPointsPerAmount() {
		return pointsPerAmount;
	}

	public void setPointsPerAmount(double pointsPerAmount) {
		this.pointsPerAmount = pointsPerAmount;
	}

}

