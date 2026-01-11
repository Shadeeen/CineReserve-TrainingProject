package com.example.finalproject.controller;
import com.example.finalproject.dto.auth.LoginUser;
import com.example.finalproject.dto.user.UserMapper;
import com.example.finalproject.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class LoginController {

    LoginService loginService;
    UserMapper userMapper;

    public LoginController(LoginService loginService, UserMapper userMapper) {
        this.loginService = loginService;
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUser dto) {
        return ResponseEntity.ok(loginService.login(dto));
    }

    @PostMapping("/refresh")
    public Map<String, String> refresh(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        return loginService.refreshToken(refreshToken);

    }
}
