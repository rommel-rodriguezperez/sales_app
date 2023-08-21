package com.mybank.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "SalesPersons")
public class SalesPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "salesPerson")
    private List<Sale> sales;

    private double monthlyGoal;


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

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	public double getMonthlyGoal() {
		return monthlyGoal;
	}

	public void setMonthlyGoal(double monthlyGoal) {
		this.monthlyGoal = monthlyGoal;
	}


}
