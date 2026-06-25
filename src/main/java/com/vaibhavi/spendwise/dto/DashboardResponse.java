package com.vaibhavi.spendwise.dto;

public class DashboardResponse {

    private Double totalExpenses;
    private Integer expenseCount;

    public DashboardResponse() {
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