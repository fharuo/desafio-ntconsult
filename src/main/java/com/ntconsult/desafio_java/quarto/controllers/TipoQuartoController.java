package com.ntconsult.desafio_java.quarto.controllers;

import com.ntconsult.desafio_java.quarto.models.TipoQuarto;
import com.ntconsult.desafio_java.quarto.services.TipoQuartoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-quarto")
@AllArgsConstructor
public class TipoQuartoController {

    private final TipoQuartoService tipoQuartoService;

    @PostMapping
    public ResponseEntity<TipoQuarto> criarTipoQuarto(@RequestBody TipoQuarto tipoQuarto) {
        TipoQuarto novoTipoQuarto = tipoQuartoService.criarTipoQuarto(tipoQuarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTipoQuarto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoQuarto> obterTipoQuartoPorId(@PathVariable Long id) {
        return tipoQuartoService.obterTipoQuartoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TipoQuarto>> listarTiposQuarto() {
        List<TipoQuarto> tiposQuarto = tipoQuartoService.listarTiposQuarto();
        return ResponseEntity.ok(tiposQuarto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoQuarto(@PathVariable Long id) {
        tipoQuartoService.deletarTipoQuarto(id);
        return ResponseEntity.noContent().build();
    }
}