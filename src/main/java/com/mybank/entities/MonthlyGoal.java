package com.mybank.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "MonthlyGoals",
       uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "month", "year"}))
public class MonthlyGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Seller seller;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    @Column(name = "goal_amount")
    private double goalAmount;
    
    @Transient
    private int progress = 0;

	public int getProgress() {
	    double totalSalesAmount = 0.0;
	    int progress;

		List<Sale> monthSales =seller.getSales();

	    for (Sale sale : seller.getSales()) {
	        if (isSaleInMonthAndYear(sale, month, year)) {
	            totalSalesAmount += sale.calculateTotalPoints();
	        }
	    }
	    progress = (int) Math.round((goalAmount/totalSalesAmount) * 100);
		return progress;
	}
	
	private boolean isSaleInMonthAndYear(Sale sale, int month, int year) {
	    Date saleDate = sale.getDate();

	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(saleDate);

	    int saleMonth = calendar.get(Calendar.MONTH) + 1; // Adding 1 because months are 0-based
	    int saleYear = calendar.get(Calendar.YEAR);

	    return saleMonth == month && saleYear == year;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getGoalAmount() {
		return goalAmount;
	}

	public void setGoalAmount(double goalAmount) {
		this.goalAmount = goalAmount;
	}


	public void setProgress(int progress) {
		this.progress = progress;
	}
 

}

