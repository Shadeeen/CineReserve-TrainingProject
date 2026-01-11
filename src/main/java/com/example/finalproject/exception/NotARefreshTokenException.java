package com.example.finalproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotARefreshTokenException extends RuntimeException {
    public NotARefreshTokenException(String message) {
        super(message);
    }
}
