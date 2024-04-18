package org.example.srg3springminiproject.repository;
import org.apache.ibatis.annotations.*;
import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.request.CategoryRequest;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface CategoryRepository {
    @Select("""
       SELECT * FROM categories_tb  LIMIT #{limit} OFFSET #{offset} ;
    """)
    @Results(id = "categoryMapper", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "users", column = "user_id", one = @One(select = "org.example.srg3springminiproject.repository.UserRepository.getUserById"))
    })
    List<Category> findAllCategory(Integer offset, Integer limit,Long userId);

    @Select("""
        SELECT * FROM categories_tb WHERE category_id=#{id} AND user_id =#{userId} ;
    """)
    @ResultMap("categoryMapper")
    Category findCategoryById(Integer id, Long userId);

    @Select("SELECT category_id, name,description FROM categories_tb WHERE category_id = #{id}")
    @ResultMap("categoryMapper")
    Category findCategoryByCate(Integer id);

    @Select("""
        INSERT INTO categories_tb (name,description,user_id) values (#{category.name},#{category.description},#{userId}) RETURNING *;
    """)
    @ResultMap("categoryMapper")
    Category insertCategory(@Param("category") CategoryRequest categoryRequest, long userId);


    @Select("""
        UPDATE categories_tb set name = (#{category.name}),description = (#{category.description}) WHERE category_id = #{id} RETURNING *
    """)
    @ResultMap("categoryMapper")
    Category updateCategory(Integer id,@Param("category") CategoryRequest categoryRequest);

    @Select("""
        DELETE  FROM categories_tb WHERE category_id = #{id} RETURNING *;
    """)
    @ResultMap("categoryMapper")
    Category removeCategory(Integer id);

}
