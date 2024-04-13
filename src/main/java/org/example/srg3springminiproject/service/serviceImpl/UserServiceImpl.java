package org.example.srg3springminiproject.service.serviceImpl;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.example.srg3springminiproject.config.PasswordConfig;
import org.example.srg3springminiproject.jwt.JWTService;
import org.example.srg3springminiproject.model.Otp;
import org.example.srg3springminiproject.model.User;
import org.example.srg3springminiproject.model.request.ForgetRequest;
import org.example.srg3springminiproject.model.request.LoginRequest;
import org.example.srg3springminiproject.model.request.RegisterRequest;
import org.example.srg3springminiproject.model.response.AuthResponse;
import org.example.srg3springminiproject.model.response.UserResponse;
import org.example.srg3springminiproject.repository.OtpRepository;
import org.example.srg3springminiproject.repository.UserRepository;
import org.example.srg3springminiproject.service.AuthService;
import org.example.srg3springminiproject.service.UserService;
import org.example.srg3springminiproject.util.EmailUtil;
import org.example.srg3springminiproject.util.OtpUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpUtil otpUtil;
    private final EmailUtil emailUtil;
    private final AuthService authService;
    private final PasswordConfig passwordConfig;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    @Override
    public UserResponse register(RegisterRequest registerRequest) throws MessagingException {
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword()) || registerRequest.getPassword().length() < 8 ) {
            throw new IllegalArgumentException("Passwords do not match or have at least 8 characters");
        } else {
            registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        }
        String otpCode = otpUtil.generateOtp();
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        User savedUser = userRepository.save(user);
        Otp otp = new Otp();
        otp.setUserId(savedUser.getUserId());
        otp.setOtpCode(otpCode);
        otp.setIssuedAt(new Timestamp(System.currentTimeMillis()));
        otp.setExpirationTime(calculateExpirationTime());
        otpRepository.insertOtp(otp);
        emailUtil.sendOtpEmail(savedUser.getEmail(), otpCode);
        return new UserResponse(savedUser.getUserId(), savedUser.getEmail());
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        UserDetails userDetails = authService.loadUserByUsername(loginRequest.getEmail());
        System.out.println(userDetails);
        if (userDetails != null) {
            User user = userRepository.getUserByEmail(loginRequest.getEmail());
            if (user != null) {
                Otp latestOtp = otpRepository.getOtpByUserId(user.getUserId());
                if (latestOtp == null || !latestOtp.isVerified()) {
                    return new AuthResponse("Your account is not verified yet, please try again.");
                }
                if (!passwordConfig.passwordEncoder().matches(loginRequest.getPassword(), userDetails.getPassword())) {
                    return new AuthResponse("Passwords do not match");
                }
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
                if (authentication.isAuthenticated()) {
                    String token = jwtService.generateToken(userDetails.getUsername());
                    return new AuthResponse(token);
                }
            } else {
                return new AuthResponse("User not found with email " + loginRequest.getEmail());
            }
        }
        return null;
    }

    @Override
    public boolean verifyOtp(String otpCode) {
        System.out.println("OTP code is: " + otpCode);
        Otp latestOtp = otpRepository.getLatestOtpByCode(otpCode);
        if (latestOtp != null && !latestOtp.isVerified()) {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            Timestamp expirationTime = latestOtp.getExpirationTime();
            if (expirationTime.after(currentTime)) {
                if (latestOtp.getOtpCode().equals(otpCode)) {
                    if (!latestOtp.isVerified()) {
                        latestOtp.setVerified(true);
                        otpRepository.updateOtp(latestOtp);
                    }
                    return true;
                }
            } else {
                System.out.println("The OTP has expired: " + expirationTime);
                return false;
            }
        }
        return false;
    }

    @Override
    public String resendOtp(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return "User with email " + email + " not found.";
        }
        String newOtpCode = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, newOtpCode);
        } catch (MessagingException e) {
            return "Failed to send OTP email. Please try again later.";
        }
        Otp existingOtp = otpRepository.getLatestUnverifiedOtpByEmail(email);
        if (existingOtp == null) {
            return "Failed to update OTP. Please try again.";
        }
        existingOtp.setOtpCode(newOtpCode);
        existingOtp.setIssuedAt(new Timestamp(System.currentTimeMillis()));
        existingOtp.setExpirationTime(calculateExpirationTime());
        otpRepository.updateOtp(existingOtp);
        return "OTP resent successfully.";
    }

    @Override
    public UserResponse forgetPassword(ForgetRequest forgetRequest, String email) {
        if (!forgetRequest.getPassword().equals(forgetRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        User user = userRepository.updatePassword(forgetRequest,email);
        return modelMapper.map(user, UserResponse.class);
    }

    private Timestamp calculateExpirationTime() {
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTimeMillis = currentTimeMillis + (2 * 30 * 1000);
        return new Timestamp(expirationTimeMillis);
    }

}
