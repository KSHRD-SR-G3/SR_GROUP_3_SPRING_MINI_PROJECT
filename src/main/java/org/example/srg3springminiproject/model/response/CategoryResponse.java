package org.example.srg3springminiproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryResponse {
    private Integer categoryId;
    private String name;
    private String description;
}
