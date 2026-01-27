package com.example.finalproject.service;

import com.example.finalproject.dto.movie.CreatMovieDTO;
import com.example.finalproject.dto.movie.MovieMapper;
import com.example.finalproject.dto.movie.ReadMovieDTO;
import com.example.finalproject.dto.movie.UpdateMovieDTO;
import com.example.finalproject.exception.InvalidCastMember;
import com.example.finalproject.exception.InvalidGenreException;
import com.example.finalproject.exception.InvalidMovieId;
import com.example.finalproject.model.*;
import com.example.finalproject.repository.CastMemberRepository;
import com.example.finalproject.repository.GenreRepository;
import com.example.finalproject.repository.MovieRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MovieService {

    MovieRepository movieRepository;
    GenreRepository genreRepository;
    CastMemberRepository castMemberRepository;
    MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, CastMemberRepository castMemberRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.castMemberRepository = castMemberRepository;
        this.movieMapper = movieMapper;
    }

    public ReadMovieDTO createMovie(@Valid CreatMovieDTO movie) {

        Movie createMovie = movieMapper.toEntity(movie);

        movieGenre(createMovie, movie.getGenreIds());
        movieCastMember(createMovie, movie.getCastMemberIds());

        Movie savedMovie = movieRepository.save(createMovie);

        return readMovie(savedMovie);
    }


    public void movieGenre(Movie newMovie, List<Long> genresIds) {

        List<Genre> existGenres = genreRepository.findAllById(genresIds);

        if (genresIds.size() != existGenres.size()) {
            throw new InvalidGenreException("Invalid genre id");
        }

        for (Genre genre : existGenres) {
            MovieGenre movieGenre = new MovieGenre();
            movieGenre.setMovie(newMovie);
            movieGenre.setGenre(genre);
            newMovie.getMovieGenres().add(movieGenre);
        }
    }

    public void movieCastMember(Movie newMovie, List<Long> castMembersIds) {

        List<CastMember> existMembers = castMemberRepository.findAllById(castMembersIds);

        if (existMembers.size() != castMembersIds.size()) {
            throw new InvalidCastMember("Invalid member id");
        }

        for (CastMember member : existMembers) {
            MovieCastMember movieCastMember = new MovieCastMember();
            movieCastMember.setMovie(newMovie);
            movieCastMember.setCastMember(member);
            newMovie.getMovieCastMembers().add(movieCastMember);
        }
    }

    public ReadMovieDTO readMovie(Movie movie) {

        ReadMovieDTO readMovieDTO = movieMapper.toReadDto(movie);

        readMovieDTO.setGenres(movie.getMovieGenres().stream().map(movieGenre -> movieGenre.getGenre().getGenreName()).toList());
        readMovieDTO.setCastMembers(movie.getMovieCastMembers().stream().map(castMember -> castMember.getCastMember().getCastMemberName()).toList());

        return readMovieDTO;

    }

    public Page<ReadMovieDTO> getMovies(Pageable pageable) {
        Page<Movie> moviePage = movieRepository.findAll(pageable);

        return moviePage.map(this::readMovie);
    }

    public ReadMovieDTO getMovieByID(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new InvalidMovieId("Movie with id " + movieId + " not found")
                );
        return readMovie(movie);

    }

    public void deleteMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new InvalidMovieId("Movie not found"));
        movie.setDeletedAt(LocalDateTime.now());
    }


    public ReadMovieDTO updateMovie(Long movieId, UpdateMovieDTO dto) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new InvalidMovieId("Movie not found"));

        if (dto.getMovieName() != null) {
            movie.setMovieName(dto.getMovieName());
        }

        if (dto.getDuration() != null) {
            movie.setDuration(dto.getDuration());
        }

        if (dto.getMovieDescription() != null) {
            movie.setMovieDescription(dto.getMovieDescription());
        }

        if (dto.getReleaseDate() != null) {
            movie.setReleaseDate(dto.getReleaseDate());
        }

        if (dto.getGenreIds() != null) {
            movie.getMovieGenres().clear();
            movieGenre(movie, dto.getGenreIds());
        }

        if (dto.getCastMemberIds() != null) {
            movie.getMovieCastMembers().clear();
            movieCastMember(movie, dto.getCastMemberIds());
        }

        return readMovie(movie);
    }

    public Page<ReadMovieDTO> movieSearch(
            LocalDate fromDate,
            LocalDate toDate,
            List<String> genres,
            String memberName,
            int page,
            int size
    ) {
        if (genres != null && genres.isEmpty()) {
            genres = null;
        }

        if (memberName != null && memberName.isBlank()) {
            memberName = null;
        }


        Pageable pageable = PageRequest.of(page, size, Sort.by("releaseDate").descending());

        return movieRepository
                .movieSearch(fromDate, toDate, genres, memberName, pageable).map(movie -> readMovie(movie));
    }
}
