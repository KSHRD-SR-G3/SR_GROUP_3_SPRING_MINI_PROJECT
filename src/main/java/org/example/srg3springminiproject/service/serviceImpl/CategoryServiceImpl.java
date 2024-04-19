package org.example.srg3springminiproject.service.serviceImpl;
import lombok.AllArgsConstructor;
import org.example.srg3springminiproject.exception.NotFoundException;
import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.request.CategoryRequest;
import org.example.srg3springminiproject.model.response.CategoryResponse;
import org.example.srg3springminiproject.repository.CategoryRepository;
import org.example.srg3springminiproject.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final UserServiceImpl userServiceImpl;
    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Category> findAllCategory(Integer offset, Integer limit) {
        long userId = userServiceImpl.getUsernameOfCurrentUser();
        offset = (offset - 1) * limit;
        return categoryRepository.findAllCategory(offset, limit,userId);
    }

    @Override
    public CategoryResponse findCategoryById(Integer id) {
        long UserId = userServiceImpl.getUsernameOfCurrentUser();
        Category category = categoryRepository.findCategoryById(id,UserId);

        if (category == null) {
            throw new NotFoundException("The category with id " + id + " doesn't exist.");
        }
        else {
            return modelMapper.map(category, CategoryResponse.class);
        }
    }

    @Override
    public Category insertCategory(CategoryRequest categoryRequest) {
       long UserId = userServiceImpl.getUsernameOfCurrentUser();
        System.out.println(UserId);
        Category categoryId = categoryRepository.insertCategory(categoryRequest,UserId);
        return categoryId;
    }

    @Override
    public CategoryResponse updateCategory(Integer id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.updateCategory(id, categoryRequest);

        if (category == null) {
            throw new NotFoundException("The category with id " + id + " doesn't exist.");
        }
        else {
            return modelMapper.map(category, CategoryResponse.class);
        }
    }

    @Override
    public Boolean removeCategory(Integer id) {

        if (categoryRepository.removeCategory(id) == null) {
            throw new NotFoundException("The category with id " + id + " doesn't exist.");
        }
        else {
            return categoryRepository.removeCategory(id);
        }
    }

}