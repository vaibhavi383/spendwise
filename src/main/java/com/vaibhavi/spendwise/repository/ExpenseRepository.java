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
}
