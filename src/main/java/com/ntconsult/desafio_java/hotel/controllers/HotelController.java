package com.ntconsult.desafio_java.hotel.controllers;

import com.ntconsult.desafio_java.hotel.models.Hotel;
import com.ntconsult.desafio_java.hotel.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/api/hoteis")
    public ResponseEntity<List<Hotel>> pesquisarHoteis(
            @RequestParam(required = false) String localizacao,
            @RequestParam(required = false) LocalDate dataCheckin,
            @RequestParam(required = false) LocalDate dataCheckout,
            @RequestParam(required = false) Integer numeroQuartos,
            @RequestParam(required = false) Integer numeroHospedes) {

        List<Hotel> hoteis = hotelService.pesquisarHoteis(localizacao, dataCheckin, dataCheckout, numeroQuartos, numeroHospedes);
        return ResponseEntity.ok(hoteis);
    }
}
