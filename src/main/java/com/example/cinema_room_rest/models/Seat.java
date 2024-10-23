package com.example.cinema_room_rest.models;

import java.util.UUID;

public class Seat {
    private final int row;
    private final int column;
    private final int price;
    private boolean bought;

    private UUID uuid;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        price = row <= 4 ? 10 : 8;
        bought = false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public boolean isBought() {
        return bought;
    }

    public UUID getUuid() { return  uuid; }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
