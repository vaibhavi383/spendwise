package com.vaibhavi.spendwise.dto;

public class DashboardResponse {

    private Double totalExpenses;
    private Integer expenseCount;

    private Double foodExpenses;
    private Double entertainmentExpenses;

    public DashboardResponse() {
    }
    
    public Double getFoodExpenses() {
        return foodExpenses;
    }

    public void setFoodExpenses(Double foodExpenses) {
        this.foodExpenses = foodExpenses;
    }

    public Double getEntertainmentExpenses() {
        return entertainmentExpenses;
    }

    public void setEntertainmentExpenses(Double entertainmentExpenses) {
        this.entertainmentExpenses = entertainmentExpenses;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public Integer getExpenseCount() {
        return expenseCount;
    }

    public void setExpenseCount(Integer expenseCount) {
        this.expenseCount = expenseCount;
    }
}