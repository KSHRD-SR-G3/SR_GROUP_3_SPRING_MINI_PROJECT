package org.example.srg3springminiproject.controller;
import org.example.srg3springminiproject.model.Expense;
import org.example.srg3springminiproject.model.dto.request.ExpenseRequest;
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

        @GetMapping
        public ResponseEntity<APIResponse<List<Expense>>> findAllExpense(
                @RequestParam(defaultValue = "1") Integer offset,
                @RequestParam(defaultValue = "3") Integer limit){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new APIResponse<>(
                            "Find All Expense Successful",
                            expenseService.findAllExpense(offset,limit),
                            HttpStatus.OK,
                           new Date()
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
                           new Date()

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
                            new Date()
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
                          new Date()
                    )
            );
        }


}
