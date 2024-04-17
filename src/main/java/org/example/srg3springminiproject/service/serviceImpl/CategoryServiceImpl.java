package org.example.srg3springminiproject.service.serviceImpl;

import lombok.AllArgsConstructor;
import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.request.CategoryRequest;
import org.example.srg3springminiproject.repository.CategoryRepository;
import org.example.srg3springminiproject.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public Category updateCategory(Integer id, CategoryRequest categoryRequest) {
        return categoryRepository.updateCategory(id,categoryRequest);
    }
    @Override
    public Category removeCategory(Integer id)
    {
        return categoryRepository.removeCategory(id);
    }
}
