package com.example.finalproject.service;

import com.example.finalproject.exception.*;
import com.example.finalproject.model.Hall;
import com.example.finalproject.model.Movie;
import com.example.finalproject.model.Screening;
import com.example.finalproject.model.Seat;
import com.example.finalproject.repository.HallRepository;
import com.example.finalproject.repository.MovieRepository;
import com.example.finalproject.repository.ScreeningRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ScreeningService {

    ScreeningRepository screeningRepository;
    HallRepository hallRepository;
    MovieRepository movieRepository;

    public ScreeningService(ScreeningRepository screeningRepository, HallRepository hallRepository, MovieRepository movieRepository) {
        this.screeningRepository = screeningRepository;
        this.hallRepository = hallRepository;
        this.movieRepository = movieRepository;
    }

    public Screening createScreening(Screening screening) {

        if (screening.getStartTime().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Start time is before now");
        }
        Hall hall = hallRepository.findById(screening.getHall().getHallId())
                .orElseThrow(() -> new NotFoundException("Hall not found"));

        Movie movie = movieRepository.findById(screening.getMovie().getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found"));

        LocalDateTime newStart = screening.getStartTime();
        LocalDateTime end = newStart.plusMinutes(movie.getDuration() + 30);


        if (screeningRepository.existsOverlap(hall.getHallId(), newStart, end)) {
            throw new BadRequestException(" hall already has an overlapping screening");
        }

        Screening newScreening = new Screening();
        newScreening.setHall(hall);
        newScreening.setMovie(movie);
        newScreening.setStartTime(newStart);

        return screeningRepository.save(newScreening);
    }

    public List<Seat> gitSeatsInScreening(Long screeningId) {
        Screening screening = screeningRepository.findById(screeningId).
                orElseThrow(() -> new NotFoundException("Screening not found"));

        List<Seat> seats = screening.getHall().getSeats();

        return seats;
    }

}
