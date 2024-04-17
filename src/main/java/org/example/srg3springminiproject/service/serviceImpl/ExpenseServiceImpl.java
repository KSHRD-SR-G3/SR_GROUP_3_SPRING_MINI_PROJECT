package org.example.srg3springminiproject.service.serviceImpl;

import lombok.AllArgsConstructor;
import org.example.srg3springminiproject.model.Expense;
import org.example.srg3springminiproject.model.request.ExpenseRequest;
import org.example.srg3springminiproject.repository.ExpenseRepository;
import org.example.srg3springminiproject.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserServiceImpl userService;



    @Override
    public List<Expense> findAllExpense(Integer offset, Integer limit) {
        offset=(offset-1) *limit;
        return expenseRepository.findAllExpense(offset,limit);
    }

    @Override
    public Expense findExpenseById(Integer id) {
        return expenseRepository.findExpenseById(id);
    }

    @Override
    public Expense saveExpense(ExpenseRequest expenseRequest) {
        Long UserId = userService.getUsernameOfCurrentUser();
        Expense expenseId = expenseRepository.saveExpense(expenseRequest,UserId);
        return expenseId;
    }

    @Override
    public Expense updateExpense(Integer id, ExpenseRequest expenseRequest) {
        Long UserId= userService.getUsernameOfCurrentUser();
        Expense expenseId = expenseRepository.updateExpense(id,expenseRequest,UserId);
        return expenseId;
    }


}
