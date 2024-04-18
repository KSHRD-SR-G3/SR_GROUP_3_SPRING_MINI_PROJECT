package org.example.srg3springminiproject.service;


import org.example.srg3springminiproject.model.Expense;

import java.util.List;

public interface ExpenseService {
    List<Expense> getAllExpense(int offset, int limit,  String sortBy,String orderByStr);

    void deleteExpense(int id);
}
