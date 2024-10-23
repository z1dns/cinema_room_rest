package com.example.cinema_room_rest.DTO;

import java.util.List;

public class CinemaDTO {
    private int rows;
    private int columns;
    private List<SeatDTO> seats;

    public CinemaDTO() {
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }
}
