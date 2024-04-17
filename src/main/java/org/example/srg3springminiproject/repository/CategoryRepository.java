package org.example.srg3springminiproject.repository;

import org.apache.ibatis.annotations.*;
import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.request.CategoryRequest;

@Mapper
public interface CategoryRepository {

    //Update category to database
    @Select("""
    UPDATE categories_tb set name = (#{category.name}),description = (#{category.description}) WHERE category_id = #{id} RETURNING *
""")
    @Results(id = "categoryMapper",value = {
            @Result(property = "category_id",column = "categoryId"),
            @Result(property = "name",column = "name"),
            @Result(property = "description",column = "description"),
    })
    Category updateCategory(Integer id,@Param("category") CategoryRequest categoryRequest);

    //Delete category from database
    @Select("""
            DELETE  FROM categories_tb WHERE category_id = #{id} RETURNING *;
""")
    @ResultMap("categoryMapper")
    Category removeCategory(Integer id);
}
