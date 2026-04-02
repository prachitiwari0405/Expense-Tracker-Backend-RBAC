package com.expensetracker.controller;
import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import com.expensetracker.util.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    // CREATE (ADMIN only)
    @PostMapping
    public Expense createExpense(
            @RequestBody Expense expense,
            @RequestHeader String role) {

        RoleUtil.checkAccess(role, "ADMIN");

        return service.createExpense(expense);
    }

    // GET ALL (all roles)
    @GetMapping
    public List<Expense> getAllExpenses(
            @RequestHeader String role) {

        RoleUtil.checkAccess(role, "ADMIN", "ANALYST", "VIEWER");

        return service.getAllExpenses();
    }

    // FILTER (ADMIN + ANALYST)
    @GetMapping("/category")
    public List<Expense> getByCategory(
            @RequestParam String category,
            @RequestHeader String role) {

        RoleUtil.checkAccess(role, "ADMIN", "ANALYST");

        return service.getByCategory(category);
    }

    // DELETE (ADMIN only)
    @DeleteMapping("/{id}")
    public String deleteExpense(
            @PathVariable Long id,
            @RequestHeader String role) {

        RoleUtil.checkAccess(role, "ADMIN");

        service.deleteExpense(id);
        return "Deleted successfully";
    }
}