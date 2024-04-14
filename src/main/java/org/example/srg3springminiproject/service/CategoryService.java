package org.example.srg3springminiproject.service;


import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.dto.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategory(Integer offset, Integer limit);

    Category findCategoryById(Integer id);

    Category saveCategory(CategoryRequest categoryRequest);
}
