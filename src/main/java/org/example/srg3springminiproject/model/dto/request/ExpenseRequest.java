package org.example.srg3springminiproject.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.srg3springminiproject.model.Category;
import org.example.srg3springminiproject.model.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {
    private int amount;
    private String description;
    private LocalDateTime date;
    private Integer categoryId;


}
