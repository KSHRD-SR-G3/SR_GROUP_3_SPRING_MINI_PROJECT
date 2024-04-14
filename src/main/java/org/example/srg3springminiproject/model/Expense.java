package org.example.srg3springminiproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    private Integer expenseId;
    private int amount;
    private String description;
    private LocalDateTime date;
    private Category categories;
    private User user;
}
