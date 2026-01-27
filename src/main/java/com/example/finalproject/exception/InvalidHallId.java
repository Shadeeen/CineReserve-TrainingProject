package com.example.finalproject.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidHallId extends RuntimeException {
    public InvalidHallId(String message) {
        super(message);
    }
}
