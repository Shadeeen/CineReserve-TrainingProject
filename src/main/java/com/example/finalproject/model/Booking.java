package com.example.finalproject.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Where;


@Entity
@Table(
        name = "booking",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_screening_seat",
                        columnNames = {"screening_id", "seat_id"}
                )
        }
)
@Where(clause = "deleted_at IS NULL")
public class Booking extends Basic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long bookingReference;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(Long bookingReference) {
        this.bookingReference = bookingReference;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }
}
