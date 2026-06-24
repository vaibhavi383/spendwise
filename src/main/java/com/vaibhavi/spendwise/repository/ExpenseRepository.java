package com.vaibhavi.spendwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.vaibhavi.spendwise.entity.Expense;
import java.time.LocalDate;
public interface ExpenseRepository
        extends JpaRepository<Expense, Long> {
	
	List<Expense> findByUserId(Long userId);
	
	List<Expense> findByCategory(String category);
	
	List<Expense> findByDate(LocalDate date);
	
	List<Expense> findByDateBetween(
	        LocalDate startDate,
	        LocalDate endDate);
	
	List<Expense> findByUserIdAndCategory(
	        Long userId,
	        String category);
	
	List<Expense> findByUserIdAndDate(
	        Long userId,
	        LocalDate date);
	
	List<Expense> findByUserIdAndDateBetween(
			Long userId,
			LocalDate startDate,
			LocalDate endDate);
}
