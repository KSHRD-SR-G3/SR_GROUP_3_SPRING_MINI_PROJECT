package org.example.srg3springminiproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Otp {
    private Integer optId;
    private String optCode;
    private LocalDateTime issued_at;
    private LocalDateTime expiration;
    private Boolean verify;
    private User users;
}
