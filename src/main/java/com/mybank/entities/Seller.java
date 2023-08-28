package com.mybank.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;

@Entity
@DiscriminatorValue("SELLER")
public class Seller extends Employee {

    @JsonManagedReference
    @OneToMany(mappedBy = "seller")
    private List<Sale> sales = new ArrayList<>(); // Employees managed by this manager

    @JsonManagedReference
    @OneToMany(mappedBy = "seller")
    private List<MonthlyGoal> monthlyGoals = new ArrayList<>();

    public void addSale(Sale sale) {
        sales.add(sale);
        sale.setSeller(this); // Assuming there's a method in Sale to set the seller
    }

    public void removeSale(Sale sale) {
        sales.remove(sale);
        sale.setSeller(null); // Assuming there's a method in Sale to set the seller
    }

    public void addMonthlyGoal(MonthlyGoal goal) {
        monthlyGoals.add(goal);
        goal.setSeller(this); // Assuming there's a method in MonthlyGoal to set the seller
    }

    public void removeMonthlyGoal(MonthlyGoal goal) {
        monthlyGoals.remove(goal);
        goal.setSeller(null); // Assuming there's a method in MonthlyGoal to set the seller
    }
}
