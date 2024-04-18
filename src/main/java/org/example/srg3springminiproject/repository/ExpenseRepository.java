package org.example.srg3springminiproject.repository;

import org.apache.ibatis.annotations.*;
import org.example.srg3springminiproject.model.Expense;
import org.example.srg3springminiproject.model.request.ExpenseRequest;

import java.util.List;

@Mapper
public interface ExpenseRepository {

    @Select("""

        select * from expenses_tb WHERE user_id =#{UserId} order by ${sortBy} ${orderByStr} LIMIT #{limit} OFFSET #{offset};
        
    """)
    @Results(id="expenseMapping", value = {
            @Result(property = "user",column = "user_id",one = @One(select = "org.example.srg3springminiproject.repository.UserRepository.getUserById")),
            @Result(property = "categories",column = "category_id",one = @One(select = "org.example.srg3springminiproject.repository.CategoryRepository.findCategoryByCate")),
            @Result(property = "expenseId", column = "expense_id"),

    })
    List<Expense> getAllExpense(int offset, int limit, String sortBy,String orderByStr,Long UserId);



    @Select("""
            SELECT * FROM expenses_tb WHERE expense_id= #{id} AND user_id =#{userId}
    """)
    @ResultMap("expenseMapping")
    Expense findExpenseById(Integer id,Long userId);



    @Select(""" 
            INSERT INTO  expenses_tb (amount,description,date,category_id,user_id)  VALUES (#{expense.amount},#{expense.description},#{expense.date},#{expense.categoryId},#{UserId} )RETURNING *;
    """)
    @ResultMap("expenseMapping")
    Expense saveExpense(@Param("expense") ExpenseRequest expenseRequest,long UserId);



    @Select("""
            UPDATE  expenses_tb SET amount=#{expense.amount}, description=#{expense.description},date=#{expense.date},category_id=#{expense.categoryId},user_id=#{UserId} WHERE expense_id=#{id}
    RETURNING *;
    """)
    @ResultMap("expenseMapping")
    Expense updateExpense(Integer id , @Param("expense") ExpenseRequest expenseRequest,long UserId);



    @Select("""
            DELETE FROM  expenses_tb WHERE expense_id=#{id}
    """)
    Boolean deleteExpense(Integer id);
}
