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
        offset=(offset-1)*limit;
        return categoryRepository.findAllCategory(offset,limit);
    }

//    @Override
//    public Category findCategoryById(Integer id) {
//
//        return categoryRepository.findCategoryById(id);
//    }
    @Override
    public CategoryResponse findCategoryById(Integer id) {
        Category category = categoryRepository.findCategoryById(id);

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
        Category categoryId=categoryRepository.insertCategory(categoryRequest,UserId);
        return categoryId;
    }

}
