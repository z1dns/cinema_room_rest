package com.example.cinema_room_rest.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseSeat {
    private String token;
    @JsonProperty("ticket")
    private SeatDTO seat;

    public PurchaseSeat() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
    }
}
