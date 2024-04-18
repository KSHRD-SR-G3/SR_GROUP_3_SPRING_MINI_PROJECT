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
    public List<Expense> getAllExpense(int offset, int limit, String sortBy,String orderByStr) {
        offset = (offset - 1) * limit;
        return expenseRepository.getAllExpense(offset,limit,sortBy,orderByStr);
    }

    @Override
    public void deleteExpense(int id) {
        expenseRepository.deleteExpense(id);
    }


}
