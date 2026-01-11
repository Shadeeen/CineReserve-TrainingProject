package com.example.finalproject.service;

import com.example.finalproject.dto.auth.LoginUser;
import com.example.finalproject.dto.user.UserMapper;
import com.example.finalproject.exception.InvalidRefreshTokenException;
import com.example.finalproject.exception.InvalidUser;
import com.example.finalproject.exception.NotARefreshTokenException;
import com.example.finalproject.model.User;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService {

    UserRepository userRepository;
    UserMapper userMapper;


    public LoginService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Map<String, String> login(LoginUser dto) {

        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new InvalidUser("invalid email or password"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new InvalidUser("invalid email or password");
        }

        String accessToken =
                JwtUtil.generateAccessToken(user.getEmail(), user.getRole());

        String refreshToken =
                JwtUtil.generateRefreshToken(user.getEmail());

        return Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken,
                "email", user.getEmail(),
                "role", user.getRole()

        );
    }

    public Map<String, String> refreshToken(String refreshToken) {

        if (!JwtUtil.validateToken(refreshToken)) {
            throw new InvalidRefreshTokenException("Invalid refresh token");
        }

        if (!"REFRESH".equals(JwtUtil.extractType(refreshToken))) {
            throw new NotARefreshTokenException("Token is not a refresh token");
        }

        String email = JwtUtil.extractEmail(refreshToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidUser("User not found"));

        String newAccessToken =
                JwtUtil.generateAccessToken(user.getEmail(), user.getRole());

        return Map.of(
                "accessToken", newAccessToken
        );
    }

}
