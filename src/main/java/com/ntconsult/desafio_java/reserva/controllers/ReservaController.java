package com.ntconsult.desafio_java.reserva.controllers;

import com.ntconsult.desafio_java.reserva.dtos.ReservaRequestDTO;
import com.ntconsult.desafio_java.reserva.dtos.ReservaResponseDTO;
import com.ntconsult.desafio_java.reserva.models.Reserva;
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
        Reserva reserva = reservaService.criarReserva(reservaRequestDTO).orElse(null);

        if (reserva == null) {
            return ResponseEntity.status(409).build();
        }

        ReservaResponseDTO responseDTO = new ReservaResponseDTO();
        responseDTO.setReservaId(reserva.getId());
        responseDTO.setQuartoId(reserva.getQuarto().getId());
        responseDTO.setDataCheckin(reserva.getDataCheckin());
        responseDTO.setDataCheckout(reserva.getDataCheckout());
        responseDTO.setNomeCliente(reserva.getNomeCliente());
        responseDTO.setContatoCliente(reserva.getContatoCliente());
        responseDTO.setStatusReserva(reserva.getStatus().getDescricao());

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
}