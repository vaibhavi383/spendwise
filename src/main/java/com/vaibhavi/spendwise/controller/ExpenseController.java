package com.vaibhavi.spendwise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vaibhavi.spendwise.dto.ExpenseRequest;
import com.vaibhavi.spendwise.entity.Expense;
import com.vaibhavi.spendwise.service.ExpenseService;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense addExpense(
            @RequestBody ExpenseRequest request) {

        return expenseService.addExpense(request);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }
    
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }
    
    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "Expense deleted successfully";
    }
    
    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expenseDetails) {

        return expenseService.updateExpense(id, expenseDetails);
    }
    
    @GetMapping("/user/{userId}/total")
    public Double getTotalExpenses(@PathVariable Long userId) {
        return expenseService.getTotalExpenses(userId);
    }
}