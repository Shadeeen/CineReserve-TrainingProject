package com.example.finalproject.service;

import com.example.finalproject.exception.InvalidMovieId;
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
        if (!(status.equals("Maintenance") || status.equals("Available"))) {
            throw new InvalidMovieId("seat status should be Maintenance or Active");
        }
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new InvalidMovieId("seat not found"));

        seat.setStatus(status);
        return seatRepository.save(seat);
    }
}
