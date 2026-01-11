package com.example.finalproject.dto.movie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

public class CreatMovieDTO {

    @NotBlank(message = "Movie title must not be blank")
    private String movieName;

    @NotNull(message = "Movie duration is required")
    @Positive(message = "Movie duration must be greater than 0")
    private Integer duration;

    private String movieDescription;

    @NotNull(message = "Release date is required")
    private LocalDate releaseDate;

    @NotEmpty(message = "At least one genre is required")
    private List<Long> genreIds;

    @NotEmpty(message = "At least one cast member is required")
    private List<Long> castMemberIds;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }

    public List<Long> getCastMemberIds() {
        return castMemberIds;
    }

    public void setCastMemberIds(List<Long> castMemberIds) {
        this.castMemberIds = castMemberIds;
    }
}
