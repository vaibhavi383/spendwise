package com.vaibhavi.spendwise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vaibhavi.spendwise.dto.DashboardResponse;
import com.vaibhavi.spendwise.dto.ExpenseRequest;
import com.vaibhavi.spendwise.entity.Expense;
import com.vaibhavi.spendwise.service.ExpenseService;
import java.time.LocalDate;
import java.time.YearMonth;
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
    
    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(
            @PathVariable String category) {

        return expenseService.getExpensesByCategory(category);
    }
    
    @GetMapping("/date/{date}")
    public List<Expense> getExpensesByDate(
    		@PathVariable LocalDate date) {
    	
        return expenseService.getExpensesByDate(date);
    }
    
    @GetMapping("/month/{month}")
    public List<Expense> getExpensesByMonth(
    		@PathVariable String month){
    	YearMonth ym = YearMonth.parse(month);
    	LocalDate startDate = ym.atDay(1);
    	LocalDate endDate = ym.atEndOfMonth();
    	return expenseService.getExpensesByMonth(startDate,endDate);
    }
    
    @GetMapping("/category/{category}/total")
    public Double getTotalExpensesByCategory(
            @PathVariable String category) {

        return expenseService
                .getTotalExpensesByCategory(category);
    }
    
    @GetMapping("/{id}/category/{category}")
    public List<Expense> getExpensesByUserAndCategory(
            @PathVariable Long id,
            @PathVariable String category) {

        return expenseService.getExpensesByUserAndCategory(id,category);
    }
    
    @GetMapping ("/{id}/date/{date}")
    public List<Expense> getExpensesByUserIdAndDate(
    		@PathVariable Long id,
    		@PathVariable LocalDate date) {
    	
    	return expenseService.getExpensesByUserAndDate(id,date);
    }
    
    @GetMapping("/{id}/month/{month}/total")
    public Double getTotalExpensesByUserAndMonth(
            @PathVariable Long id,
            @PathVariable String month) {

        YearMonth ym = YearMonth.parse(month);

        LocalDate startDate = ym.atDay(1);

        LocalDate endDate = ym.atEndOfMonth();

        return expenseService.getTotalExpensesByUserIdAndDateBetween(
                id,
                startDate,
                endDate);
        }
    	
    @GetMapping("/dashboard/{id}")
    public DashboardResponse getDashboard(
            @PathVariable Long id) {

        return expenseService.getDashboard(id);
    }
}