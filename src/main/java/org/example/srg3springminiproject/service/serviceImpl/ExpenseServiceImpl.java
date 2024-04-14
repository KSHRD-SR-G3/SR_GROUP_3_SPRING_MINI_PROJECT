package org.example.srg3springminiproject.service.serviceImpl;
import org.example.srg3springminiproject.model.Expense;
import org.example.srg3springminiproject.repository.ExpenseRepository;
import org.example.srg3springminiproject.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    public final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getAllExpense(int offset, int limit) {
        offset = (offset - 1) * limit;
        return expenseRepository.getAllExpense(offset,limit);
    }

    @Override
    public Expense deleteExpense(int id) {
        return expenseRepository.deleteExpense(id);
    }
}
