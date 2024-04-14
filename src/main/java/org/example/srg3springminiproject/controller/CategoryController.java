package org.example.srg3springminiproject.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.dto.request.CategoryRequest;
import org.example.srg3springminiproject.model.response.APIResponse;
import org.example.srg3springminiproject.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping()
    public ResponseEntity<APIResponse<List<Category>>> findAllCategory(@Positive
                                                                 @RequestParam(defaultValue = "1") Integer offset ,
                                                                       @RequestParam(defaultValue = "3") Integer limit){
        return  ResponseEntity.status(HttpStatus.OK).body(
                new APIResponse<>(
                        "Find all Category Successful",
                        categoryService.findAllCategory(offset,limit),
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Category>> findCategoryById(@PathVariable Integer id){
        Category findcategory=categoryService.findCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new APIResponse<>(
                        "Find By Id Category Successful",
                        findcategory,
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }
    @PostMapping()
    public ResponseEntity<APIResponse<Category>> saveCategory(@RequestBody @Valid CategoryRequest categoryRequest){
        Category insertcategory=categoryService.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new APIResponse<>(
                        "Category saved successfully",
                        insertcategory,
                        HttpStatus.CREATED,
                        LocalDateTime.now()
                )
        );
    }
}
