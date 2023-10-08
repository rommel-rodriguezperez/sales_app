package com.mybank.dto;

import java.util.List;

public class SellerMonthlyPointsDTO {

	private List<ProductSummary> productSummaries;
    private double totalPoints;
    
	public List<ProductSummary> getProductSummaries() {
		return productSummaries;
	}
	public void setProductSummaries(List<ProductSummary> productSummaries) {
		this.productSummaries = productSummaries;
	}
	public double getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(double totalPoints) {
		this.totalPoints = totalPoints;
	}
}
