package com.vaibhavi.spendwise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vaibhavi.spendwise.exception.UserNotFoundException;
import com.vaibhavi.spendwise.entity.Expense;
import com.vaibhavi.spendwise.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }
    
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
    
    public Expense updateExpense(Long id, Expense expenseDetails) {

    	Expense expense = expenseRepository.findById(id)
    	        .orElseThrow(() ->
    	                new UserNotFoundException("Expense not found"));

        expense.setTitle(expenseDetails.getTitle());
        expense.setAmount(expenseDetails.getAmount());
        expense.setCategory(expenseDetails.getCategory());
        expense.setDate(expenseDetails.getDate());

        return expenseRepository.save(expense);
    }
}