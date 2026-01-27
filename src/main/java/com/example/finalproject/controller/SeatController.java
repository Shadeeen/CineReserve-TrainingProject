package com.example.finalproject.controller;

import com.example.finalproject.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/seats")
public class SeatController {

    SeatService seatService;

    SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PatchMapping("/{id}/status")
    public String updateSeatStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        if (seatService.updateSeat(id, status) != null) {
            return "updated successfully ";
        }
        return "failed ";

    }
}
