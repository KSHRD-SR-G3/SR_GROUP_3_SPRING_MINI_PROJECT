package org.example.srg3springminiproject.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.example.srg3springminiproject.exception.InvalidInputException;
import org.example.srg3springminiproject.exception.NotFoundException;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/Auth")
@AllArgsConstructor

public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<APIResponse<UserResponse>> register(@RequestBody @Valid  RegisterRequest registerRequest) throws MessagingException {
        if (!isValidPassword(registerRequest.getPassword())) throw new InvalidInputException("Password must be at least 8 characters long and contain at least one digit, one letter, and one special character.");

        UserResponse userResponse = userService.register(registerRequest);
        System.out.println(userResponse);
        System.out.println(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>(
                "Please Check Email for Verify OTP Code", userResponse, HttpStatus.CREATED,new Date()
        ));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        AuthResponse response = userService.login(loginRequest);
//        System.out.println(response);
//        System.out.println(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse<>(
                "Login Successful", response, HttpStatus.CREATED,new Date()
        ));
    }
    @PutMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam @Positive (message = "OTP code must be a positive number") String otpCode) {
//        if (userService.verifyOtp(otpCode)) {
//
//            APIResponse<String> response = APIResponse.<String>builder()
//                    .message("OTP verified successfully")
//                    .status(HttpStatus.OK)
//                    .creationDate(new Date())
//                    .payload("Your OTP has been successfully verified.")
//                    .build();
//            return ResponseEntity.ok(response);
//        } else  {
//            APIResponse<String> response = APIResponse.<String>builder()
//                    .message("Invalid or expired OTP code: "+ new Date())
//                    .status(HttpStatus.BAD_REQUEST)
//                    .creationDate(new Date())
//                    .payload("The OTP code you provided is either invalid or expired. Please try again.")
//                    .build();
//            return ResponseEntity.badRequest().body(response);
//        }
        boolean response = userService.verifyOtp(otpCode);
        //System.out.println(response);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(
                "Your account is Verify successfully", response, HttpStatus.OK, new Date()
        ));
    }
    @PostMapping("/resend-otp")
//    public ResponseEntity<APIResponse<String>> resendOtp(@RequestParam @Valid String email) {
//        String message = userService.resendOtp(email);
//        HttpStatus status = HttpStatus.OK;
//        if (!message.equals("OTP resent successfully.")) {
//            status = HttpStatus.BAD_REQUEST;
//        }
//        APIResponse<String> response = APIResponse.<String>builder()
//                .message(message)
//                .status(HttpStatus.OK)
//                .creationDate(new Date())
//                .build();
//        return ResponseEntity.status(status).body(response);
//    }
    public ResponseEntity<String> resendOtp(@RequestParam @Valid String email) {
        String message = userService.resendOtp(email);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PutMapping("/forget-password")
    public ResponseEntity<UserResponse> forgetPassword(@RequestBody @Valid ForgetRequest forgetRequest, @RequestParam @Valid String email) {
        if (!isValidPassword(forgetRequest.getPassword())) throw new InvalidInputException("Password must be at least 8 characters long and contain at least one digit, one letter, and one special character.");
            //return ResponseEntity.badRequest().body(new APIResponse<>("Password must be at least 8 characters long and contain at least one digit, one letter, and one special character.", null, HttpStatus.BAD_REQUEST, new Date()));
        UserResponse user  = userService.forgetPassword(forgetRequest, email);
//        APIResponse<UserResponse> response = APIResponse.<UserResponse>builder()
//                .message("Your Password is Changed Successfully: ")
//                .status(HttpStatus.CREATED)
//                .creationDate(new Date())
//                .payload(user)
//                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
    }
}
