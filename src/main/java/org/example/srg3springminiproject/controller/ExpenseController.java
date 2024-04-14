package org.example.srg3springminiproject.controller;

import org.example.srg3springminiproject.model.Expense;
import org.example.srg3springminiproject.model.dto.request.ExpenseRequest;
import org.example.srg3springminiproject.model.response.APIResponse;
import org.example.srg3springminiproject.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/expense")
public class ExpenseController {
        private final ExpenseService expenseService;

        public ExpenseController(ExpenseService expenseService) {
            this.expenseService = expenseService;
        }

        @GetMapping
        public ResponseEntity<APIResponse<List<Expense>>> findAllExpense(
                @RequestParam(defaultValue = "1") Integer offset,
                @RequestParam(defaultValue = "3") Integer limit){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new APIResponse<>(
                            "Find All Expense Successful",
                            expenseService.findAllExpense(offset,limit),
                            HttpStatus.OK,
                            LocalDateTime.now()
                    )
            );
        }

        @GetMapping("/{id}")
        public ResponseEntity<APIResponse<Expense>> findExpenseById(@PathVariable Integer id){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new APIResponse<>(
                            "Find Expense By Id Is Successful",
                            expenseService.findExpenseById(id),
                            HttpStatus.OK,
                            LocalDateTime.now()

                    )
            );
        }


        @PostMapping
        public ResponseEntity<APIResponse<Expense>>  saveExpense(@RequestBody ExpenseRequest expenseRequest){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new APIResponse<>(
                            "Insert New Expense Is Successful",
                            expenseService.saveExpense(expenseRequest),
                            HttpStatus.OK,
                            LocalDateTime.now()
                    )
            );
        }

        @PutMapping
        public ResponseEntity<APIResponse<Expense>> updateExpense(@RequestBody ExpenseRequest expenseRequest){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new APIResponse<>(
                          "Update Expense IS Successful",
                          expenseService.updateExpense(expenseRequest),
                          HttpStatus.OK,
                          LocalDateTime.now()
                    )
            );
        }


}
