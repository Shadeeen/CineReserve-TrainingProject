package com.example.finalproject.service;

import com.example.finalproject.exception.BadRequestException;
import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.model.Seat;
import com.example.finalproject.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class SeatService {

    SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat updateSeat(Long id, String status) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new NotFoundException("seat not found"));

        if (!(status.equals("Maintenance") || status.equals("Available"))) {
            throw new BadRequestException("seat status should be Maintenance or Active");
        }

        seat.setStatus(status);
        return seatRepository.save(seat);
    }
}
