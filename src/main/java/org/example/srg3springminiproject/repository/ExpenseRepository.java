package org.example.srg3springminiproject.repository;

import org.apache.ibatis.annotations.*;
import org.example.srg3springminiproject.model.Expense;

import java.util.List;

@Mapper
public interface ExpenseRepository {

    @Select("""
        select * from expenses_tb LIMIT #{limit} OFFSET #{offset};
    """)
    @Results(id="expenseMapping", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "categoryId", column = "categoryId"),
    })
    List<Expense> getAllExpense(int offset, int limit, String sortBy, boolean orderBy);


    @Select("""
        delete from expenses_tb where expense_id = #{expenseId}
    """)
    @ResultMap("expenseMapping")
    void deleteExpense(int id);
}
