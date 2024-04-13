package org.example.srg3springminiproject.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.srg3springminiproject.model.User;
import org.example.srg3springminiproject.model.request.ForgetRequest;
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
                "Please Check Email for Verify OTP Code", userResponse, HttpStatus.CREATED,new Date()
        ));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid() LoginRequest loginRequest) {
        AuthResponse response = userService.login(loginRequest);
        if (response != null && response.getAccessToken() != null) {
            return ResponseEntity.ok().body(response.getAccessToken());
        } else {
            return ResponseEntity.badRequest().body("Login Failed, Please try again");
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
    @PostMapping("/resend-otp")
    public ResponseEntity<APIResponse<String>> resendOtp(@RequestParam String email) {
        String message = userService.resendOtp(email);
        HttpStatus status = HttpStatus.OK;
        if (!message.equals("OTP resent successfully.")) {
            status = HttpStatus.BAD_REQUEST;
        }
        APIResponse<String> response = APIResponse.<String>builder()
                .message(message)
                .status(HttpStatus.OK)
                .creationDate(new Date())
                .build();
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/forget-password")
    public ResponseEntity<APIResponse<UserResponse>> forgetPassword(@RequestBody ForgetRequest forgetRequest, @RequestParam String email) {
        UserResponse user  = userService.forgetPassword(forgetRequest, email);
        APIResponse<UserResponse> response = APIResponse.<UserResponse>builder()
                .message("Your Password is Changed Successfully: ")
                .status(HttpStatus.CREATED)
                .creationDate(new Date())
                .payload(user)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        String currentUserEmail = userService.getUsernameOfCurrentUser();
        User userProfile = userService.getUserCurrentByEmail(currentUserEmail);
        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User profile not found");
        }
    }
}
