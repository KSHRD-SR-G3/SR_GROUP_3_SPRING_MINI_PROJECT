package org.example.srg3springminiproject.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.request.CategoryRequest;
import org.example.srg3springminiproject.model.response.CategoryResponse;
import org.example.srg3springminiproject.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/categories")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    //update category
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable @Positive Integer id, @RequestBody CategoryRequest categoryRequest){
        return categoryService.updateCategory(id,categoryRequest);

    }
    //Delete Category
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse<Category>> removeCategory(@PathVariable Integer id){
        if (categoryService.removeCategory(id)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new CategoryResponse<>(
                            "category Notfound",
                            HttpStatus.NOT_FOUND,
                            LocalDateTime.now()
                    )
            );
        }
        categoryService.removeCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new CategoryResponse<>(
                        "delete category successful",
                        HttpStatus.OK,
                        LocalDateTime.now()
                )
        );
    }
}
