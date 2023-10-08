package com.mybank.dto;

import java.util.List;


import java.util.List;

import java.util.List;

public class SalesSummaryForYearAndMonthDTO {
    private String sellerName;
    private List<ProductSummary> productSummaries;
    private double totalPoints;

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

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
