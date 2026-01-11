package com.example.finalproject.dto.movie;

import java.time.LocalDate;
import java.util.List;

public class UpdateMovieDTO {

        private String movieName;
        private Integer duration;
        private String movieDescription;
        private LocalDate releaseDate;
        private List<Long> genreIds;
        private List<Long> castMemberIds;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Long> getCastMemberIds() {
        return castMemberIds;
    }

    public void setCastMemberIds(List<Long> castMemberIds) {
        this.castMemberIds = castMemberIds;
    }

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
