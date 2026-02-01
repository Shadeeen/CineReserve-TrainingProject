package com.example.finalproject.service;

import com.example.finalproject.dto.booking.CreateBookingRequest;
import com.example.finalproject.exception.*;
import com.example.finalproject.model.Booking;
import com.example.finalproject.model.Screening;
import com.example.finalproject.model.Seat;
import com.example.finalproject.model.User;
import com.example.finalproject.repository.BookingRepository;
import com.example.finalproject.repository.ScreeningRepository;
import com.example.finalproject.repository.SeatRepository;
import com.example.finalproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookingService {
    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private SeatRepository seatRepository;
    private ScreeningRepository screeningRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, SeatRepository seatRepository, ScreeningRepository screeningRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.seatRepository = seatRepository;
        this.screeningRepository = screeningRepository;
    }

    public String createBooking(CreateBookingRequest booking) {

        User user = userRepository.findById(booking.getUserId()).orElseThrow(() -> new NotFoundException("user not found"));
        Screening screening = screeningRepository.findById(booking.getScreeningId()).orElseThrow(() -> new NotFoundException("screening not found"));

        String reference = java.util.UUID.randomUUID().toString();

        try {
            for (Long seatId : booking.getSeatIds()) {

                Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new NotFoundException("Seat not found"));

                if (!seat.getHall().getHallId().equals(screening.getHall().getHallId())) {
                    throw new NotFoundException("Seat does not belong to screening hall");
                }

                Booking b = new Booking();
                b.setUser(user);
                b.setScreening(screening);
                b.setSeat(seat);
                b.setBookingReference(reference);
                bookingRepository.save(b);
            }
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("One or more seats are unavailable");
        }
        return reference;
    }
}
