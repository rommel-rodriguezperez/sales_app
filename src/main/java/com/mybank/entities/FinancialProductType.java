package com.mybank.entities;


public enum FinancialProductType {
    CLASSIC_CREDIT_CARD(10),
    GOLD_CREDIT_CARD(20),
    PLATINUM_CREDIT_CARD(40),
    MORTGAGE(0.5),
    PERSONAL_LOAN(0.3);

    private final double pointsPerAmount;

    FinancialProductType(double pointsPerAmount) {
        this.pointsPerAmount = pointsPerAmount;
    }

    public double getPointsPerAmount() {
        return pointsPerAmount;
    }
}
