package org.example.srg3springminiproject.controller;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.example.srg3springminiproject.model.request.RegisterRequest;
import org.example.srg3springminiproject.model.response.APIResponse;
import org.example.srg3springminiproject.model.response.UserResponse;
import org.example.srg3springminiproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/Auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<APIResponse<UserResponse>> register(@RequestBody RegisterRequest registerRequest) throws MessagingException {
        UserResponse userResponse = userService.register(registerRequest);
        System.out.println(userResponse);
        System.out.println(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>(
                "Check your Email for Verify Email", userResponse, HttpStatus.CREATED,new Date()
        ));
    }

}
