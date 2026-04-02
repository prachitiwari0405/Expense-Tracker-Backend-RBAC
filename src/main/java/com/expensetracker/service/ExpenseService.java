package com.expensetracker.service;

import com.expensetracker.model.Expense;
import com.expensetracker.model.Type;
import com.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepo;

    // create expense
    public Expense createExpense(Expense expense) {

        if (expense.getAmount() <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }

        return expenseRepo.save(expense);
    }

    // get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    // delete expense
    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }

    // filter by category
    public List<Expense> getByCategory(String category) {
        return expenseRepo.findByCategory(category);
    }

    // dashboard logic
    public double getTotalIncome() {
        return expenseRepo.findAll().stream()
                .filter(e -> e.getType() == Type.INCOME)
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        return expenseRepo.findAll().stream()
                .filter(e -> e.getType() == Type.EXPENSE)
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}