package com.example.finalproject.controller;

import com.example.finalproject.dto.movie.CreatMovieDTO;
import com.example.finalproject.dto.movie.ReadMovieDTO;
import com.example.finalproject.dto.movie.UpdateMovieDTO;
import com.example.finalproject.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReadMovieDTO createMovie(
            @RequestBody @Valid CreatMovieDTO createMovie) {

        return movieService.createMovie(createMovie);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ReadMovieDTO> getMovies(@PageableDefault(size = 10, sort = "movieName") Pageable pageable) {
        return movieService.getMovies(pageable);
    }

    @GetMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public ReadMovieDTO getMovie(@PathVariable Long movieId) {
        return movieService.getMovieByID(movieId);
    }

    @DeleteMapping("/{movieId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
    }

    @PatchMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public ReadMovieDTO updateMovie(@PathVariable Long movieId, @RequestBody UpdateMovieDTO dto) {
        return movieService.updateMovie(movieId, dto);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<ReadMovieDTO> searchMovie(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
                                          @RequestParam(required = false) List<String> genres,
                                          @RequestParam(required = false) String member,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return movieService.movieSearch(fromDate, toDate, genres, member, page, size);
    }
}
