package com.mybank.dto;

public class ProductSummary {
    private String productName;
    private double pointsPerAmount;
    private double totalAmountSold;
    private double totalPointsByProduct;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPointsPerAmount() {
		return pointsPerAmount;
	}
	public void setPointsPerAmount(double pointsPerAmount) {
		this.pointsPerAmount = pointsPerAmount;
	}
	public double getTotalAmountSold() {
		return totalAmountSold;
	}
	public void setTotalAmountSold(double totalAmountSold) {
		this.totalAmountSold = totalAmountSold;
	}
	public double getTotalPointsByProduct() {
		return totalPointsByProduct;
	}
	public void setTotalPointsByProduct(double totalPointsByProduct) {
		this.totalPointsByProduct = totalPointsByProduct;
	}


    
}
