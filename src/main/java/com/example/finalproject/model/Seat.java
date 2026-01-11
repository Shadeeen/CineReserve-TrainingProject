package com.example.finalproject.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Where;

@Entity
@Table(name="seats")
@Where(clause = "deleted_at IS NULL")
public class Seat extends Basic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seat_id")
    private Long seatId;

    private String status;

    @JoinColumn(name = "seat_column")
    private int seatColumn;

    @JoinColumn(name = "seat_row")
    private int seatRow;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }



    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }
}
