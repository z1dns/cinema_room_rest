package com.example.cinema_room_rest.controllers;

import com.example.cinema_room_rest.exceptions.CinemaException;
import com.example.cinema_room_rest.DTO.*;
import com.example.cinema_room_rest.models.Seat;
import com.example.cinema_room_rest.models.CinemaModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CinemaController {
    private final CinemaModel cinema = new CinemaModel(9, 9);
    @GetMapping("/seats")
    public CinemaDTO getSeats() {
        CinemaDTO cinemaDTO = new CinemaDTO();
        cinemaDTO.setRows(cinema.getRows());
        cinemaDTO.setColumns(cinema.getColumns());
        List<SeatDTO> seatDTOList = new ArrayList<>();
        for (var availableSeat : cinema.getAvailableSeats()) {
            seatDTOList.add(SeatMapper.toDTO(availableSeat));
        }
        cinemaDTO.setSeats(seatDTOList);
        return cinemaDTO;
    }

    @GetMapping("/stats")
    public ResponseEntity getStats(@RequestParam(value = "password", required = false, defaultValue = "") String password) {
        if (!"super_secret".equals(password)) {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
        Statistics statistics = new Statistics();
        statistics.setAvailable(cinema.getAvailableSeats().size());
        statistics.setPurchased(cinema.getPurchasedSeats().size());
        statistics.setIncome(cinema.getIncome());
        return ResponseEntity.ok(statistics);
    }

    @PostMapping("/purchase")
    public PurchaseSeat purchaseTicket(@RequestBody PurchaseInfo purchaseInfo) {
        Seat seat = cinema.purchaseTicket(purchaseInfo.getRow(), purchaseInfo.getColumn());
        PurchaseSeat purchaseSeat = new PurchaseSeat();
        purchaseSeat.setToken(seat.getUuid().toString());
        purchaseSeat.setSeat(SeatMapper.toDTO(seat));
        return purchaseSeat;
    }

    @PostMapping("/return")
    public ReturnTicket returnTicket(@RequestBody ReturnInfo returnInfo) {
        Seat seat = cinema.returnTicket(returnInfo.getToken());
        ReturnTicket returnTicket = new ReturnTicket();
        returnTicket.setSeat(SeatMapper.toDTO(seat));
        return returnTicket;
    }

    @ExceptionHandler(CinemaException.class)
    public ResponseEntity<CustomErrorMessage> handlePurchaseException(
            CinemaException e, WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage( e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
