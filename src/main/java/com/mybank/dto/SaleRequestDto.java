package com.mybank.dto;

import java.util.Date;

public class SaleRequestDto {

    private Long salesPersonId;
    private Long customerId;
    private Long financialProductId;
    private Date date;
    private double amount;
	public Long getSalesPersonId() {
		return salesPersonId;
	}
	public void setSalesPersonId(Long salesPersonId) {
		this.salesPersonId = salesPersonId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getFinancialProductId() {
		return financialProductId;
	}
	public void setFinancialProductId(Long financialProductId) {
		this.financialProductId = financialProductId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}


}

