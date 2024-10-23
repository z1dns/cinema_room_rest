package com.example.cinema_room_rest.models;

import com.example.cinema_room_rest.exceptions.CinemaException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CinemaModel {
    private final int rows;
    private final int columns;
    private final List<Seat> seats;

    public CinemaModel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        seats = new ArrayList<>();
        for (int rowIdx = 0; rowIdx < rows; ++rowIdx) {
            for (int columnIdx = 0; columnIdx < columns; ++columnIdx) {
                seats.add(new Seat(rowIdx + 1, columnIdx + 1));
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public List<Seat> getAvailableSeats() {
        return seats.stream().
                filter(s -> !s.isBought()).
                collect(Collectors.toList());
    }

    public List<Seat> getPurchasedSeats() {
        return seats.stream().
                filter(Seat::isBought).
                collect(Collectors.toList());
    }

    public int getIncome() {
        return getPurchasedSeats().stream().
                map(Seat::getPrice).
                reduce(0, Integer::sum);
    }

    public Seat purchaseTicket(int row, int column) {
        Seat seat = getSeatByPosition(row, column);
        if (seat == null) throw new CinemaException("The number of a row or a column is out of bounds!");
        if (seat.isBought()) throw new CinemaException("The ticket has been already purchased!");
        seat.setBought(true);
        seat.setUuid(UUID.randomUUID());
        return seat;
    }

    public Seat returnTicket(String token) {
        Seat seat = getSeatByToken(token);
        if (seat == null) throw new CinemaException("Wrong token!");
        seat.setBought(false);
        seat.setUuid(null);
        return seat;
    }

    private Seat getSeatByPosition(int row, int column) {
        for (var seat : seats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return seat;
            }
        }
        return null;
    }

    private Seat getSeatByToken(String token) {
        for (var seat : seats) {
            UUID uuid = seat.getUuid();
            if (uuid != null && uuid.toString().equals(token)) {
                return seat;
            }
        }
        return null;
    }
}
