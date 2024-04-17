package org.example.srg3springminiproject.controller;

import org.example.srg3springminiproject.model.Expense;
import org.example.srg3springminiproject.model.response.APIResponse;
import org.example.srg3springminiproject.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/expense")
@SecurityRequirement(name = "bearerAuth")
public class ExpenseController {
    public final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Expense>>> getAllExpense(@RequestParam(defaultValue = "1") int offset,
                                                                    @RequestParam(defaultValue = "3") int limit,
                                                                    @RequestParam(defaultValue = "expense_id") String sortBy,
                                                                    @RequestParam(defaultValue = "false") boolean orderBy){

//        if (!orderBy) {
//            APIResponse<List<Expense>> errorResponse = APIResponse.<List<Expense>>builder()
//                    .message("Invalid orderBy parameter. It should be 'asc' or 'desc'.")
//                    .status(HttpStatus.BAD_REQUEST)
//                    .creationDate(new Date())
//                    .build();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//        }

        APIResponse<List<Expense>> response = APIResponse.<List<Expense>>builder()
                .message("All expenses have been successfully fetched.")
                .payload(expenseService.getAllExpense(offset, limit,sortBy, false))
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Expense>> deleteExpense(@PathVariable int id){
        expenseService.deleteExpense(id);
        APIResponse<Expense> response = APIResponse.<Expense>builder()
                .message("The expense has been successfully deleted.")
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
