package org.example.srg3springminiproject.service;


import org.example.srg3springminiproject.model.Expense;

import org.example.srg3springminiproject.model.request.ExpenseRequest;


import java.util.List;

public interface ExpenseService {



    List<Expense> findAllExpense(Integer offset, Integer limit);

    Expense findExpenseById(Integer id);

    Expense saveExpense(ExpenseRequest expenseRequest);

    Expense updateExpense(Integer id, ExpenseRequest expenseRequest);


}
