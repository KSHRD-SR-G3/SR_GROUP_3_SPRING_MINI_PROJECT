package org.example.srg3springminiproject.service.serviceImpl;

import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.dto.request.CategoryRequest;
import org.example.srg3springminiproject.repository.CategoryRepository;
import org.example.srg3springminiproject.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategory(Integer offset, Integer limit) {
        offset=(offset-1)*limit;
        return categoryRepository.findAllCategory(offset,limit);
    }

    @Override
    public Category findCategoryById(Integer id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public Category saveCategory(CategoryRequest categoryRequest) {
        return categoryRepository.saveCategory(categoryRequest);
    }
}
