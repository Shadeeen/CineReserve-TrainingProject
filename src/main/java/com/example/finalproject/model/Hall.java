package com.example.finalproject.model;


import jakarta.persistence.*;
import org.hibernate.annotations.Where;

@Entity
@Where(clause = "deleted_at IS NULL")
public class Hall extends Basic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hall_id")
    private Long hallId;

    @Column(name="hall_name")
    private String hallName;

    @Column(name="rows_number")
    private int rowsNumber;

    @Column(name="columns_number")
    private int columnsNumber;

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
}
