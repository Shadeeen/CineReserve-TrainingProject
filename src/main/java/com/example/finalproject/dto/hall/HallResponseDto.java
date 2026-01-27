package com.example.finalproject.dto.hall;

import java.util.List;


public class HallResponseDto {
    private Long hallId;
    private String hallName;
    private int rowsNumber;
    private int columnsNumber;
    private List<ResponseSeatDto> seats;

    public HallResponseDto() {
    }

    public HallResponseDto(Long hallId, String hallName, int rowsNumber, int columnsNumber, List<ResponseSeatDto> seats) {
        this.hallId = hallId;
        this.hallName = hallName;
        this.rowsNumber = rowsNumber;
        this.columnsNumber = columnsNumber;
        this.seats = seats;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public void setRowsNumber(int rowsNumber) {
        this.rowsNumber = rowsNumber;
    }

    public int getColumnsNumber() {
        return columnsNumber;
    }

    public void setColumnsNumber(int columnsNumber) {
        this.columnsNumber = columnsNumber;
    }

    public List<ResponseSeatDto> getSeats() {
        return seats;
    }

    public void setSeats(List<ResponseSeatDto> seats) {
        this.seats = seats;
    }
}
