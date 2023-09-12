package com.mybank.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private Date date;
    
//    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER)
    @JsonManagedReference
    @OneToMany(mappedBy = "sale")
    private List<SaleDetail> details = new ArrayList<>();


    public int addSaleDetail(SaleDetail detail) {
		details.add(detail);
		detail.setSale(this);
		return 0;
    }

    public int removeSaleDetail(SaleDetail detail) {
		details.remove(detail);
		detail.setSale(null);
		return 0;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
