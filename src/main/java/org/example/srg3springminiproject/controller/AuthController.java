package org.example.srg3springminiproject.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.srg3springminiproject.model.request.LoginRequest;
import org.example.srg3springminiproject.model.request.RegisterRequest;
import org.example.srg3springminiproject.model.response.APIResponse;
import org.example.srg3springminiproject.model.response.AuthResponse;
import org.example.srg3springminiproject.model.response.UserResponse;
import org.example.srg3springminiproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid() LoginRequest loginRequest) {
        AuthResponse response = userService.login(loginRequest);
        if (response != null) {
            return ResponseEntity.ok(new APIResponse<>("Login successful", response, HttpStatus.OK, new Date()));
        } else {
            return ResponseEntity.badRequest().body(new APIResponse<>("Wrong email or password", null, HttpStatus.BAD_REQUEST, new Date()));
        }
    }
    @PutMapping("/verify-otp")
    public ResponseEntity<APIResponse<String>> verifyOtp(@RequestParam String otpCode) {
        if (userService.verifyOtp(otpCode)) {
            APIResponse<String> response = APIResponse.<String>builder()
                    .message("OTP verified successfully")
                    .status(HttpStatus.OK)
                    .creationDate(new Date())
                    .payload("Your OTP has been successfully verified.")
                    .build();
            return ResponseEntity.ok(response);
        } else {
            APIResponse<String> response = APIResponse.<String>builder()
                    .message("Invalid or expired OTP code: "+ new Date())
                    .status(HttpStatus.BAD_REQUEST)
                    .creationDate(new Date())
                    .payload("The OTP code you provided is either invalid or expired. Please try again.")
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

}
