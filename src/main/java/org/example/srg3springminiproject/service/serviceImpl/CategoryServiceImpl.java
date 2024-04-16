package org.example.srg3springminiproject.service.serviceImpl;
import lombok.AllArgsConstructor;
import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.request.CategoryRequest;
import org.example.srg3springminiproject.repository.CategoryRepository;
import org.example.srg3springminiproject.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
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
    public Category insertCategory(CategoryRequest categoryRequest) {
        Integer categoryId=categoryRepository.insertCategory(categoryRequest);
        return findCategoryById(categoryId);
    }

}
