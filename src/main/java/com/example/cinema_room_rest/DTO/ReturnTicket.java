package com.example.cinema_room_rest.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnTicket {
    @JsonProperty("ticket")
    private SeatDTO seat;

    public ReturnTicket() {
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
    }
}
