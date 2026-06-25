package com.vaibhavi.spendwise.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ExpenseRequest {


@NotBlank(message = "Title cannot be empty")
private String title;

@NotNull(message = "Amount is required")
@Positive(message = "Amount must be greater than 0")
private Double amount;

@NotBlank(message = "Category cannot be empty")
private String category;

@NotNull(message = "Date is required")
private LocalDate date;

@NotNull(message = "UserId is required")
private Long userId;

public ExpenseRequest() {
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public Double getAmount() {
    return amount;
}

public void setAmount(Double amount) {
    this.amount = amount;
}

public String getCategory() {
    return category;
}

public void setCategory(String category) {
    this.category = category;
}

public LocalDate getDate() {
    return date;
}

public void setDate(LocalDate date) {
    this.date = date;
}

public Long getUserId() {
    return userId;
}

public void setUserId(Long userId) {
    this.userId = userId;
}


}
