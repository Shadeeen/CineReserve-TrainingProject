package com.example.finalproject.controller;

import com.example.finalproject.dto.booking.CreateBookingRequest;
import com.example.finalproject.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    BookingService bookingService;

    public  BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public String booking(@RequestBody CreateBookingRequest booking) {
        return bookingService.createBooking(booking);
    }
}
