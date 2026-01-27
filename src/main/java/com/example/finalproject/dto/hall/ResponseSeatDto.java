package com.example.finalproject.dto.hall;

public class ResponseSeatDto {

    private Long seatId;
    private String status;
    private int seatRow;
    private int seatColumn;

    public ResponseSeatDto() {
    }

    public ResponseSeatDto(Long seatId, String status, int seatRow, int seatColumn) {
        this.seatId = seatId;
        this.status = status;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }
}
