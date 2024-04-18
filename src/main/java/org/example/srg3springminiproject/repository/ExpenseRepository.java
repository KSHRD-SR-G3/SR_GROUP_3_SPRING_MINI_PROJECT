package org.example.srg3springminiproject.repository;

import org.apache.ibatis.annotations.*;
import org.example.srg3springminiproject.model.Expense;
import org.example.srg3springminiproject.model.request.ExpenseRequest;https://github.com/KSHRD-SR-G3/SR_GROUP_3_SPRING_MINI_PROJECT/pull/23/conflict?name=src%252Fmain%252Fjava%252Forg%252Fexample%252Fsrg3springminiproject%252Frepository%252FExpenseRepository.java&ancestor_oid=af7100ef3896cf2f13bdb661e8823ce2f1f1eac9&base_oid=6203a872f261711e2047185720c0d72c25a54e2a&head_oid=d2f9e089880051ff603b2a85e12ea0bb37ce1450

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

        SELECT *FROM expenses_tb LIMIT #{limit} OFFSET #{offset};
    """)
    @Results(id="expenseMapper",value = {
            @Result(property = "expenseId",column = "expense_id"),
            @Result(property = "user",column = "user_id",one = @One(select = "org.example.srg3springminiproject.repository.UserRepository.getUserById")),
            @Result(property = "categories",column = "category_id",one = @One(select = "org.example.srg3springminiproject.repository.CategoryRepository.findCategoryByCategoryId"))
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
            UPDATE  expenses_tb SET amount=#{expense.amount}, description=#{expense.description},date=#{expense.date},category_id=#{expense.categoryId},user_id=#{UserId} WHERE expense_id=#{id} RETURNING *;
    """)
    @ResultMap("expenseMapper")
    Expense updateExpense(Integer id , @Param("expense") ExpenseRequest expenseRequest,long UserId);
}
