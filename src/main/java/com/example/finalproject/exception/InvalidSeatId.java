package com.example.finalproject.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSeatId extends RuntimeException {
    public InvalidSeatId(String message) {
        super(message);
    }
}
