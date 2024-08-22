package com.ntconsult.desafio_java.hotel.controllers;

import com.ntconsult.desafio_java.hotel.dtos.ComparacaoDTO;
import com.ntconsult.desafio_java.hotel.dtos.ComparacaoPrecoDTO;
import com.ntconsult.desafio_java.hotel.dtos.HotelDTO;
import com.ntconsult.desafio_java.hotel.services.HotelComparisonService;
import com.ntconsult.desafio_java.hotel.services.HotelService;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    public ResponseEntity<List<HotelDTO>> pesquisarHoteis(
            @RequestParam @NotNull String localizacao,
            @RequestParam @NotNull @FutureOrPresent LocalDate dataCheckin,
            @RequestParam @NotNull @FutureOrPresent LocalDate dataCheckout,
            @RequestParam @NotNull @Positive(message = "Insira um número maior que zero") Integer numeroQuartos,
            @RequestParam @NotNull @Positive(message = "Insira um número maior que zero") Integer numeroHospedes) {

        List<HotelDTO> hoteis = hotelService.pesquisarHoteis(localizacao, dataCheckin, dataCheckout, numeroQuartos, numeroHospedes);
        return ResponseEntity.ok(hoteis);
    }

    @GetMapping("/comparar/avaliacao")
    public ResponseEntity<List<ComparacaoDTO>> compararHoteisPorAvaliacao(
            @RequestParam Long primeiroHotelId,
            @RequestParam Long segundoHotelId) {

        List<ComparacaoDTO> hoteisComparados = hotelComparisonService.compararHoteisPorAvaliacao(primeiroHotelId, segundoHotelId);

        return ResponseEntity.ok(hoteisComparados);
    }

    @GetMapping("/comparar/localizacao")
    public ResponseEntity<List<ComparacaoDTO>> compararHoteisPorLocalizacao(
            @RequestParam Long primeiroHotelId,
            @RequestParam Long segundoHotelId) {

        List<ComparacaoDTO> hoteisComparados = hotelComparisonService.compararHoteisPorLocalizacao(primeiroHotelId, segundoHotelId);

        return ResponseEntity.ok(hoteisComparados);
    }

    @GetMapping("/comparar/comodidades")
    public ResponseEntity<List<ComparacaoDTO>> compararHoteisPorComodidades(
            @RequestParam Long primeiroHotelId,
            @RequestParam Long segundoHotelId) {

        List<ComparacaoDTO> hoteisComparados = hotelComparisonService.compararHoteisPorComodidades(primeiroHotelId, segundoHotelId);

        return ResponseEntity.ok(hoteisComparados);
    }

    @GetMapping("/comparar/preco")
    public ResponseEntity<List<ComparacaoPrecoDTO>> compararHoteisPorPreco(
            @RequestParam Long primeiroHotelId,
            @RequestParam Long segundoHotelId) {

        List<ComparacaoPrecoDTO> hoteisComparados = hotelComparisonService.compararHoteisPorPreco(primeiroHotelId, segundoHotelId);

        return ResponseEntity.ok(hoteisComparados);
    }
}
