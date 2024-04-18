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

        APIResponse<List<Category>> response=APIResponse.<List<Category>> builder()
                .message("All Categories have been successfully founded")
                .payload(categoryService.findAllCategory(offset,limit))
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Category>> findCategoryById(@PathVariable Integer id){
        Category findcategory=categoryService.findCategoryById(id);
        APIResponse<Category> response= APIResponse.<Category> builder()
                .message("The category have been successfully founded")
                .payload(findcategory)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

   @PostMapping()
   public Category insertCategory(@RequestBody CategoryRequest categoryRequest){
       return categoryService.insertCategory(categoryRequest);
   }

}
