package org.example.srg3springminiproject.service;


import org.example.srg3springminiproject.model.Expense;

import org.example.srg3springminiproject.model.request.ExpenseRequest;


import java.util.List;

public interface ExpenseService {

    List<Expense> getAllExpense(int offset, int limit,  String sortBy,String orderByStr);


    Expense findExpenseById(Integer id);

    Expense saveExpense(ExpenseRequest expenseRequest);

    Expense updateExpense(Integer id, ExpenseRequest expenseRequest);


    Boolean deleteExpense(Integer id);
}
