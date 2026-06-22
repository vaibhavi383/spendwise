package com.vaibhavi.spendwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhavi.spendwise.entity.Expense;

public interface ExpenseRepository
        extends JpaRepository<Expense, Long> {

}