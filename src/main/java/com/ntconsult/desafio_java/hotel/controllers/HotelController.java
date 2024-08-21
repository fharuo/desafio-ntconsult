package com.ntconsult.desafio_java.hotel.controllers;

import com.ntconsult.desafio_java.hotel.dtos.ComparacaoDTO;
import com.ntconsult.desafio_java.hotel.dtos.ComparacaoPrecoDTO;
import com.ntconsult.desafio_java.hotel.dtos.HotelDTO;
import com.ntconsult.desafio_java.hotel.models.Hotel;
import com.ntconsult.desafio_java.hotel.services.HotelComparisonService;
import com.ntconsult.desafio_java.hotel.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/hoteis")
public class HotelController {

    private final HotelService hotelService;
    
    private final HotelComparisonService hotelComparisonService;

    @GetMapping
    public ResponseEntity<List<HotelDTO>> listarHoteis() {
        List<HotelDTO> hoteis = hotelService.listarHoteis();

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

    @GetMapping("/comparar/avaliacao")
    public ResponseEntity<List<ComparacaoDTO>> compararHoteisPorAvaliacao(
            @RequestParam(required = false) Long primeiroHotelId,
            @RequestParam(required = false) Long segundoHotelId) {

        List<ComparacaoDTO> hoteisComparados = hotelComparisonService.compararHoteisPorAvaliacao(primeiroHotelId, segundoHotelId);

        return ResponseEntity.ok(hoteisComparados);
    }

    @GetMapping("/comparar/localizacao")
    public ResponseEntity<List<ComparacaoDTO>> compararHoteisPorLocalizacao(
            @RequestParam(required = false) Long primeiroHotelId,
            @RequestParam(required = false) Long segundoHotelId) {

        List<ComparacaoDTO> hoteisComparados = hotelComparisonService.compararHoteisPorLocalizacao(primeiroHotelId, segundoHotelId);

        return ResponseEntity.ok(hoteisComparados);
    }

    @GetMapping("/comparar/comodidades")
    public ResponseEntity<List<ComparacaoDTO>> compararHoteisPorComodidades(
            @RequestParam(required = false) Long primeiroHotelId,
            @RequestParam(required = false) Long segundoHotelId) {

        List<ComparacaoDTO> hoteisComparados = hotelComparisonService.compararHoteisPorComodidades(primeiroHotelId, segundoHotelId);

        return ResponseEntity.ok(hoteisComparados);
    }

    @GetMapping("/comparar/preco")
    public ResponseEntity<List<ComparacaoPrecoDTO>> compararHoteisPorPreco(
            @RequestParam(required = false) Long primeiroHotelId,
            @RequestParam(required = false) Long segundoHotelId) {

        List<ComparacaoPrecoDTO> hoteisComparados = hotelComparisonService.compararHoteisPorPreco(primeiroHotelId, segundoHotelId);

        return ResponseEntity.ok(hoteisComparados);
    }
}
