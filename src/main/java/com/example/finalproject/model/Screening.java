package com.example.finalproject.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Where(clause = "deleted_at IS NULL")
public class Screening extends Basic{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="screening_id")
    private Long screeningId;

    @Column(name="start_time")
    private Date startTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    public Long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Long screeningId) {
        this.screeningId = screeningId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
