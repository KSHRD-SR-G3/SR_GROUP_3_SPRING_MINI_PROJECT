package org.example.srg3springminiproject.controller;
import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.Expense;
import org.example.srg3springminiproject.model.response.APIResponse;
import org.example.srg3springminiproject.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;



import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")

public class CategoryController {

}
