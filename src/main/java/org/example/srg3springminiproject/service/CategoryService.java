package org.example.srg3springminiproject.service;


import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.request.CategoryRequest;
import org.example.srg3springminiproject.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategory(Integer offset, Integer limit);

    CategoryResponse findCategoryById(Integer id);

    Category insertCategory(CategoryRequest categoryRequest);

//    Category saveCategory(CategoryRequest categoryRequest);
}
