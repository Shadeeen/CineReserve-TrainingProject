package com.example.finalproject.service;

import com.example.finalproject.exception.InvalidHallName;
import com.example.finalproject.model.Hall;
import com.example.finalproject.model.Seat;
import com.example.finalproject.repository.HallRepository;
import com.example.finalproject.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HallService {

    HallRepository hallRepository;
    SeatRepository seatRepository;

    public HallService(HallRepository hallRepository, SeatRepository seatRepository) {
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
    }

    public Hall createHall(Hall hall) {

        try {
            for (int r = 1; r <= hall.getRowsNumber(); r++) {
                for (int c = 1; c <= hall.getColumnsNumber(); c++) {
                    Seat seat = new Seat();
                    seat.setSeatRow(r);
                    seat.setSeatColumn(c);
                    seat.setStatus("Available");
                    hall.addSeat(seat);
                }
            }

            return hallRepository.save(hall);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidHallName("Hall name already exists");
        }
    }

}
