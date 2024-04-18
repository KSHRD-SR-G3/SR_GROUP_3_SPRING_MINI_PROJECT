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
    private final UserServiceImpl userServiceImpl;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategory(Integer offset, Integer limit) {
        offset = (offset - 1) * limit;
        long UserId = userServiceImpl.getUsernameOfCurrentUser();
        return categoryRepository.findAllCategory(offset, limit,UserId);
    }

    @Override
    public Category findCategoryById(Integer id) {
        long UserId = userServiceImpl.getUsernameOfCurrentUser();
        return categoryRepository.findCategoryById(id,UserId);
    }

    @Override
    public Category insertCategory(CategoryRequest categoryRequest) {

       long UserId = userServiceImpl.getUsernameOfCurrentUser();
        System.out.println(UserId);
        Category categoryId = categoryRepository.insertCategory(categoryRequest,UserId);
        return categoryId;


    }


}