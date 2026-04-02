package com.expensetracker.controller;

import com.expensetracker.service.ExpenseService;
import com.expensetracker.util.RoleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private ExpenseService service;

    @GetMapping
    public Map<String, Double> getSummary(
            @RequestHeader String role) {

        RoleUtil.checkAccess(role, "ADMIN", "ANALYST");

        double income = service.getTotalIncome();
        double expense = service.getTotalExpense();

        Map<String, Double> data = new HashMap<>();
        data.put("totalIncome", income);
        data.put("totalExpense", expense);
        data.put("netBalance", income - expense);

        return data;
    }
}