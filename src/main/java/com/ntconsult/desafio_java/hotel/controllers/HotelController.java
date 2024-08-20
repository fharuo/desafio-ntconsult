package com.ntconsult.desafio_java.hotel.controllers;

import com.ntconsult.desafio_java.hotel.models.Hotel;
import com.ntconsult.desafio_java.hotel.services.HotelComparisonService;
import com.ntconsult.desafio_java.hotel.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController("/api/hoteis")
public class HotelController {

    private final HotelService hotelService;
    
    private final HotelComparisonService hotelComparisonService;

    @GetMapping
    public ResponseEntity<List<Hotel>> listarHoteis() {
        List<Hotel> hoteis = hotelService.listarHoteis();

        return ResponseEntity.ok(hoteis);
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<Hotel>> pesquisarHoteis(
            @RequestParam(required = false) String localizacao,
            @RequestParam(required = false) LocalDate dataCheckin,
            @RequestParam(required = false) LocalDate dataCheckout,
            @RequestParam(required = false) Integer numeroQuartos,
            @RequestParam(required = false) Integer numeroHospedes) {

        List<Hotel> hoteis = hotelService.pesquisarHoteis(localizacao, dataCheckin, dataCheckout, numeroQuartos, numeroHospedes);
        return ResponseEntity.ok(hoteis);
    }

    @GetMapping("/comparar/preco")
    public ResponseEntity<List<Hotel>> compararHoteisPorPreco(
            @RequestParam(required = false) String localizacao,
            @RequestParam(required = false) LocalDate dataCheckin,
            @RequestParam(required = false) LocalDate dataCheckout,
            @RequestParam(required = false) Integer numeroQuartos,
            @RequestParam(required = false) Integer numeroHospedes) {

        List<Hotel> hoteis = hotelService.pesquisarHoteis(localizacao, dataCheckin, dataCheckout, numeroQuartos, numeroHospedes);
        List<Hotel> hoteisComparados = hotelComparisonService.compararHoteisPorPreco(hoteis);
        return ResponseEntity.ok(hoteisComparados);
    }

    @GetMapping("/comparar/avaliacao")
    public ResponseEntity<List<Hotel>> compararHoteisPorAvaliacao(
            @RequestParam(required = false) String localizacao,
            @RequestParam(required = false) LocalDate dataCheckin,
            @RequestParam(required = false) LocalDate dataCheckout,
            @RequestParam(required = false) Integer numeroQuartos,
            @RequestParam(required = false) Integer numeroHospedes) {

        List<Hotel> hoteis = hotelService.pesquisarHoteis(localizacao, dataCheckin, dataCheckout, numeroQuartos, numeroHospedes);
        List<Hotel> hoteisComparados = hotelComparisonService.compararHoteisPorAvaliacao(hoteis);
        return ResponseEntity.ok(hoteisComparados);
    }

    @GetMapping("/comparar/localizacao")
    public ResponseEntity<List<Hotel>> compararHoteisPorLocalizacao(
            @RequestParam(required = true) String localizacao) {

        List<Hotel> hoteis = hotelService.pesquisarHoteis(localizacao, null, null, 0, 0);
        List<Hotel> hoteisComparados = hotelComparisonService.compararHoteisPorLocalizacao(hoteis, localizacao);
        return ResponseEntity.ok(hoteisComparados);
    }

    @GetMapping("/comparar/comodidades")
    public ResponseEntity<List<Hotel>> compararHoteisPorComodidades(
            @RequestParam(required = true) List<String> comodidades) {

        List<Hotel> hoteis = hotelService.pesquisarHoteis(null, null, null, 0, 0);
        List<Hotel> hoteisComparados = hotelComparisonService.compararHoteisPorComodidades(hoteis, comodidades);
        return ResponseEntity.ok(hoteisComparados);
    }
}
