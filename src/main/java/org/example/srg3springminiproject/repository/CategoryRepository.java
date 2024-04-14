package org.example.srg3springminiproject.repository;

import org.apache.ibatis.annotations.*;
import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.dto.request.CategoryRequest;

import java.util.List;

@Mapper
public interface CategoryRepository {
    @Select("""
       SELECT * FROM categories_tb LIMIT #{limit} OFFSET #{offset};
    """)
    @Results(id = "categoryMapper",value = {
            @Result(property = "categoryId",column = "category_id"),
            @Result(property = "users",column = "user_id")

    })
    List<Category> findAllCategory(Integer offset, Integer limit);
    @Select("""
        SELECT * FROM categories_tb WHERE category_id=#{id};
    """)
    @ResultMap("categoryMapper")
    Category findCategoryById(Integer id);
    @Select("""
        INSERT INTO categories_tb(name,description,user_id) values (#{category.name},#{category.description},#{category.users}) RETURNING *;
    """)
    @ResultMap("categoryMapper")
    Category saveCategory(@Param("category") CategoryRequest categoryRequest);
}
