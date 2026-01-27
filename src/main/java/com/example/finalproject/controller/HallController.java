package com.example.finalproject.controller;


import com.example.finalproject.dto.hall.HallMapper;
import com.example.finalproject.dto.hall.HallResponseDto;
import com.example.finalproject.model.Hall;
import com.example.finalproject.service.HallService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/halls")
public class HallController {

    HallService hallService;
    HallMapper hallMapper;

    public HallController(HallService hallService, HallMapper hallMapper) {
        this.hallService = hallService;
        this.hallMapper = hallMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HallResponseDto createHall(@RequestBody @Valid Hall hall) {
        Hall saved = hallService.createHall(hall);
        return hallMapper.toDto(saved);
    }
}
