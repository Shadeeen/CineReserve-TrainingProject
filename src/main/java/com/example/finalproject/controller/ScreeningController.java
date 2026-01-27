package com.example.finalproject.controller;

import com.example.finalproject.dto.screening.ScreeningMapper;
import com.example.finalproject.dto.screening.ScreeningResponseDto;
import com.example.finalproject.model.Screening;
import com.example.finalproject.service.ScreeningService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {
    ScreeningService screeningService;
    ScreeningMapper screeningMapper;

    public ScreeningController(ScreeningService screeningService, ScreeningMapper screeningMapper) {
        this.screeningService = screeningService;
        this.screeningMapper = screeningMapper;
    }

    @PostMapping
    public ScreeningResponseDto createScreening(@RequestBody Screening screening) {
        return screeningMapper.toDto(screeningService.createScreening(screening));
    }

}
