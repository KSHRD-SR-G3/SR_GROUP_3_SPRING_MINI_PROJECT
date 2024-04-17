package org.example.srg3springminiproject.repository;

import org.apache.ibatis.annotations.*;
import org.example.srg3springminiproject.model.Expense;
import org.example.srg3springminiproject.model.dto.request.ExpenseRequest;

import java.util.List;

@Mapper
public interface ExpenseRepository {

    @Select("""
        SELECT *FROM expenses_tb LIMIT #{limit} OFFSET #{offset};
    """)
    @Results(id="expenseMapper",value = {
            @Result(property = "expenseId",column = "expense_id"),
            @Result(property = "categories",column = "category_id",one = @One(select = "org.example.srg3springminiproject.repository.CategoryRepository.findCategoryById")),
            @Result(property = "user",column = "user_id",one = @One(select = "org.example.srg3springminiproject.repository.UserRepository.getUserById"))
    })
    List<Expense> findAllExpense(Integer offset, Integer limit);

    @Select("""
            SELECT *FROM expenses_tb WHERE expense_id= #{id}
    """)
    @ResultMap("expenseMapper")
    Expense findExpenseById(Integer id);


    @Select("""
            INSERT INTO  expenses_tb (amount,description,date,category_id,user_id)  VALUES (#{expense.amount},#{expense.description},#{expense.date},#{expense.categoryId},#{UserId} )RETURNING *;
    """)
    @ResultMap("expenseMapper")
    Expense saveExpense(@Param("expense") ExpenseRequest expenseRequest,long UserId);

    @Select("""
            
    """)
    Expense updateExpense(ExpenseRequest expenseRequest);
}
