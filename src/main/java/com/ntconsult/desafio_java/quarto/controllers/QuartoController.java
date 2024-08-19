package com.ntconsult.desafio_java.quarto.controllers;

import com.ntconsult.desafio_java.quarto.models.Quarto;
import com.ntconsult.desafio_java.quarto.services.QuartoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/quartos")
public class QuartoController {


    private final QuartoService quartoService;

    @PostMapping
    public ResponseEntity<Quarto> criarQuarto(@RequestBody Quarto quarto) {
        Quarto novoQuarto = quartoService.criarQuarto(quarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoQuarto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quarto> obterQuartoPorId(@PathVariable Long id) {
        return quartoService.obterQuartoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Quarto>> listarQuartosPorHotel(@PathVariable Long hotelId) {
        List<Quarto> quartos = quartoService.listarQuartosPorHotel(hotelId);
        return ResponseEntity.ok(quartos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarQuarto(@PathVariable Long id) {
        quartoService.deletarQuarto(id);
        return ResponseEntity.noContent().build();
    }
}
