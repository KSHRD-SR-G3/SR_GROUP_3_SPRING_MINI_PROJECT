package org.example.srg3springminiproject.service.serviceImpl;

import org.example.srg3springminiproject.model.Expense;
import org.example.srg3springminiproject.model.dto.request.ExpenseRequest;
import org.example.srg3springminiproject.repository.ExpenseRepository;
import org.example.srg3springminiproject.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

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
        Integer expenseId=expenseRepository.saveExpense(expenseRequest);
        return expenseRepository.findExpenseById(expenseId);
    }

    @Override
    public Expense updateExpense(ExpenseRequest expenseRequest) {
        return expenseRepository.updateExpense(expenseRequest);
    }
}
