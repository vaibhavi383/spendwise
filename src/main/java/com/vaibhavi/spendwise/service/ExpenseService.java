package com.vaibhavi.spendwise.service;
import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vaibhavi.spendwise.exception.UserNotFoundException;
import com.vaibhavi.spendwise.entity.Expense;
import com.vaibhavi.spendwise.repository.ExpenseRepository;
import com.vaibhavi.spendwise.dto.DashboardResponse;
import com.vaibhavi.spendwise.dto.ExpenseRequest;
import com.vaibhavi.spendwise.entity.User;
import com.vaibhavi.spendwise.repository.UserRepository;
@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(ExpenseRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with id " + request.getUserId()));

        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDate(request.getDate());

        expense.setUser(user);

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
    @Autowired
    private UserRepository userRepository;
    
    public Double getTotalExpenses(Long userId) {

        List<Expense> expenses =
                expenseRepository.findByUserId(userId);

        Double total = 0.0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }
    
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }
    
    public List<Expense> getExpensesByDate(LocalDate date) {
        return expenseRepository.findByDate(date);
    }
    
    public List<Expense> getExpensesByMonth(LocalDate startDate,LocalDate endDate) {
    	return expenseRepository.findByDateBetween(startDate, endDate);
    }
    
    public Double getTotalExpensesByCategory(
            String category) {
    	
    	List<Expense> expenses=expenseRepository.findByCategory(category);

    	double total = 0.0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }
    
    public List<Expense> getExpensesByUserAndCategory(
            Long userId,
            String category) {

        return expenseRepository.findByUserIdAndCategory(
                userId,
                category);
    }
    
    public List<Expense> getExpensesByUserAndDate(
    		Long userId,
    		LocalDate date) {
    	return expenseRepository.findByUserIdAndDate(
    			userId,
    			date);
    }
    
    public Double getTotalExpensesByUserIdAndDateBetween(
            Long userId,
            LocalDate startDate,
            LocalDate endDate) {
    	
    	List<Expense> expenses=expenseRepository.findByUserIdAndDateBetween(userId,startDate,endDate);

    	double total = 0.0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }
    
    public DashboardResponse getDashboard(
            Long userId) {

        List<Expense> expenses =
                expenseRepository.findByUserId(userId);

        Double total = 0.0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalExpenses(total);

        response.setExpenseCount(expenses.size());

        return response;
    }
}