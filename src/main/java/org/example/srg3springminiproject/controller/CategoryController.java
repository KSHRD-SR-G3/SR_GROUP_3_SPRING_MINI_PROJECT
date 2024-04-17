package org.example.srg3springminiproject.controller;
import lombok.AllArgsConstructor;
import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.request.CategoryRequest;
import org.example.srg3springminiproject.model.response.APIResponse;
import org.example.srg3springminiproject.service.CategoryService;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/v1/categories")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")

public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<APIResponse<List<Category>>> findAllCategory(@RequestParam(defaultValue = "1") Integer offset ,
                                                                       @RequestParam(defaultValue = "5") Integer limit){
        return  ResponseEntity.status(HttpStatus.OK).body(
                new APIResponse<>(
                        "All Categories have been successfully founded",
                        categoryService.findAllCategory(offset,limit),
                        HttpStatus.OK,
                        new Date()
                )
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Category>> findCategoryById(@PathVariable Integer id){
        Category findcategory=categoryService.findCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new APIResponse<>(
                        "The category have been successfully founded",
                        findcategory,
                        HttpStatus.OK,
                        new Date()
                )
        );
    }

   @PostMapping()
   public Category insertCategory(@RequestBody CategoryRequest categoryRequest){
       return categoryService.insertCategory(categoryRequest);

   }

}
