package org.example.srg3springminiproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {
    private String message;
    private T payload;
    private HttpStatus status;
    private LocalDateTime dateTime;
}
