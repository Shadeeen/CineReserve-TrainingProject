package com.example.finalproject.controller;

import com.example.finalproject.dto.hall.ResponseSeatDto;
import com.example.finalproject.mapper.ScreeningMapper;
import com.example.finalproject.mapper.SeatMapper;
import com.example.finalproject.dto.screening.ScreeningResponseDto;
import com.example.finalproject.model.Screening;
import com.example.finalproject.model.Seat;
import com.example.finalproject.service.ScreeningService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {
    ScreeningService screeningService;
    ScreeningMapper screeningMapper;
    SeatMapper seatMapper;

    public ScreeningController(ScreeningService screeningService, ScreeningMapper screeningMapper, SeatMapper seatMapper) {
        this.screeningService = screeningService;
        this.screeningMapper = screeningMapper;
        this.seatMapper = seatMapper;
    }

    @PostMapping
    public ScreeningResponseDto createScreening(@RequestBody Screening screening) {
        Screening createdScreening = screeningService.createScreening(screening);
        return screeningMapper.toDto(createdScreening);
    }

    @GetMapping("/{id}")
    public List<ResponseSeatDto> getSeats(@PathVariable Long id) {
        List<Seat> seats = screeningService.gitSeatsInScreening(id);
        return  seatMapper.toDtoList(seats);
    }
}
