package org.example.srg3springminiproject.controller;
import io.swagger.v3.oas.annotations.Parameter;
import org.example.srg3springminiproject.model.Expense;
import org.example.srg3springminiproject.model.request.ExpenseRequest;
import org.example.srg3springminiproject.model.response.APIResponse;
import org.example.srg3springminiproject.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("api/v1/expense")
public class ExpenseController {
        private final ExpenseService expenseService;

        public ExpenseController(ExpenseService expenseService) {
            this.expenseService = expenseService;
        }




































































        @GetMapping("/{id}")
        public ResponseEntity<APIResponse<Expense>> findExpenseById(@PathVariable Integer id){
            APIResponse<Expense> response= APIResponse.<Expense>builder()
                    .message("Get expense by id have been successfully ")
                    .payload(expenseService.findExpenseById(id))
                    .status(HttpStatus.OK)
                    .creationDate(new Date())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }


        @PostMapping
        public ResponseEntity<APIResponse<Expense>>  saveExpense(@RequestBody ExpenseRequest expenseRequest){
            APIResponse<Expense> response = APIResponse.<Expense>builder()
                    .message("Add new expense have been successfully")
                    .payload(expenseService.saveExpense(expenseRequest))
                    .status(HttpStatus.CREATED)
                    .creationDate(new Date())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        @PutMapping("/{id}")
        public ResponseEntity<APIResponse<Expense>> updateExpense( @RequestBody ExpenseRequest expenseRequest,@PathVariable Integer id){
           APIResponse<Expense> response = APIResponse.<Expense>builder()
                   .message("Update on expense have been successfully ")
                   .payload(expenseService.updateExpense(id,expenseRequest))
                   .status(HttpStatus.OK)
                   .creationDate(new Date())
                   .build();
           return ResponseEntity.status(HttpStatus.OK).body(response);
        }


}
