package org.example.srg3springminiproject.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.srg3springminiproject.model.response.UserResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer categoryId;
    private String name;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserResponse users;
}
