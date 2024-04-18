package org.example.srg3springminiproject.repository;

import org.apache.ibatis.annotations.*;
import org.example.srg3springminiproject.model.Expense;

import java.util.List;

@Mapper
public interface ExpenseRepository {

    @Select("""
        select * from expenses_tb order by ${sortBy} ${orderByStr} LIMIT #{limit} OFFSET #{offset};
        
    """)
    @Results(id="expenseMapping", value = {
            @Result(property = "user",column = "user_id",one = @One(select = "org.example.srg3springminiproject.repository.UserRepository.getUserById")),
            @Result(property = "categories",column = "category_id",one = @One(select = "org.example.srg3springminiproject.repository.CategoryRepository.findCategoryByCategoryId")),
            @Result(property = "expenseId", column = "expense_id"),

    })
    List<Expense> getAllExpense(int offset, int limit, String sortBy,String orderByStr);


    @Select("""
        delete from expenses_tb where expense_id = #{expenseId}
    """)
    @ResultMap("expenseMapping")
    void deleteExpense(int id);
}
