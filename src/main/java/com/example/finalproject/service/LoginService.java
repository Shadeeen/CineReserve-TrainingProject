package com.example.finalproject.service;

import com.example.finalproject.dto.user.LoginUser;
import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.exception.TokenException;
import com.example.finalproject.mapper.UserMapper;
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

        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new NotFoundException("invalid email or password"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new NotFoundException("invalid email or password");
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
            throw new TokenException("Invalid refresh token");
        }

        if (!"REFRESH".equals(JwtUtil.extractType(refreshToken))) {
            throw new TokenException("Token is not a refresh token");
        }

        String email = JwtUtil.extractEmail(refreshToken);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        String newAccessToken =
                JwtUtil.generateAccessToken(user.getEmail(), user.getRole());

        return Map.of(
                "accessToken", newAccessToken
        );
    }

}
