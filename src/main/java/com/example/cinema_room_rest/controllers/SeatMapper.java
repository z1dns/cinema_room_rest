package com.example.cinema_room_rest.controllers;

import com.example.cinema_room_rest.DTO.SeatDTO;
import com.example.cinema_room_rest.models.Seat;

public class SeatMapper {
    public static SeatDTO toDTO(Seat seat) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setRow(seat.getRow());
        seatDTO.setColumn(seat.getColumn());
        seatDTO.setPrice(seat.getPrice());
        return seatDTO;
    }

    public static Seat fromDTO(SeatDTO seatDTO) {
        return new Seat(seatDTO.getRow(), seatDTO.getColumn());
    }
}
