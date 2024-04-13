package org.example.srg3springminiproject.service.serviceImpl;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.example.srg3springminiproject.model.Otp;
import org.example.srg3springminiproject.model.User;
import org.example.srg3springminiproject.model.request.RegisterRequest;
import org.example.srg3springminiproject.model.response.UserResponse;
import org.example.srg3springminiproject.repository.OtpRepository;
import org.example.srg3springminiproject.repository.UserRepository;
import org.example.srg3springminiproject.service.UserService;
import org.example.srg3springminiproject.util.EmailUtil;
import org.example.srg3springminiproject.util.OtpUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpUtil otpUtil;
    private final EmailUtil emailUtil;
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



    private Timestamp calculateExpirationTime() {
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTimeMillis = currentTimeMillis + (2 * 30 * 1000);
        return new Timestamp(expirationTimeMillis);
    }

}
