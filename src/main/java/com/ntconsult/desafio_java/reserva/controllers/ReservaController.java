package com.ntconsult.desafio_java.reserva.controllers;

import com.ntconsult.desafio_java.reserva.dtos.ReservaRequestDTO;
import com.ntconsult.desafio_java.reserva.dtos.ReservaResponseDTO;
import com.ntconsult.desafio_java.reserva.services.ReservaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> criarReserva(@RequestBody ReservaRequestDTO reservaRequestDTO) {
        ReservaResponseDTO responseDTO = reservaService.criarReserva(reservaRequestDTO);

        if (responseDTO == null) {
            return ResponseEntity.status(409).build();
        }

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        if (reservaService.cancelarReserva(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/checkin")
    public ResponseEntity<Void> processarCheckin(@PathVariable Long id) {
        boolean checkinProcessado = reservaService.processarCheckin(id);

        if (checkinProcessado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/checkout")
    public ResponseEntity<Void> processarCheckout(@PathVariable Long id) {
        boolean checkoutProcessado = reservaService.processarCheckout(id);

        if (checkoutProcessado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}