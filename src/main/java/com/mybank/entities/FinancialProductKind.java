package com.mybank.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

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
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinancialProductKind that = (FinancialProductKind) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

