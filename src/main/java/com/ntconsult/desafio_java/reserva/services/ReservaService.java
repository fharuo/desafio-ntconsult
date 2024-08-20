package com.ntconsult.desafio_java.reserva.services;

import com.ntconsult.desafio_java.quarto.repositories.QuartoRepository;
import com.ntconsult.desafio_java.reserva.dtos.ReservaRequestDTO;
import com.ntconsult.desafio_java.reserva.models.Reserva;
import com.ntconsult.desafio_java.reserva.models.StatusReserva;
import com.ntconsult.desafio_java.reserva.repositories.ReservaRepository;
import com.ntconsult.desafio_java.reserva.repositories.StatusReservaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReservaService {

    private ReservaRepository reservaRepository;

    private QuartoRepository quartoRepository;

    private StatusReservaRepository statusReservaRepository;

    public Optional<Reserva> criarReserva(ReservaRequestDTO dto) {
        boolean disponivel = quartoRepository.isQuartoDisponivel(dto.getQuartoId(), dto.getDataCheckin(), dto.getDataCheckout());

        if (disponivel) {
            Reserva reserva = new Reserva();
            reserva.setQuarto(quartoRepository.findById(dto.getQuartoId()).orElseThrow(() -> new IllegalArgumentException("Quarto não encontrado")));
            reserva.setDataCheckin(dto.getDataCheckin());
            reserva.setDataCheckout(dto.getDataCheckout());
            reserva.setNomeCliente(dto.getNomeCliente());
            reserva.setContatoCliente(dto.getContatoCliente());
            reserva.setDetalhesPagamento(dto.getDetalhesPagamento());

            StatusReserva statusConfirmado = statusReservaRepository.findByDescricao("CONFIRMADA").orElseThrow(() -> new IllegalArgumentException("Status de reserva não encontrado"));
            reserva.setStatus(statusConfirmado);

            return Optional.of(reservaRepository.save(reserva));
        } else {
            return Optional.empty();
        }
    }

    public boolean cancelarReserva(Long id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            StatusReserva statusCancelada = statusReservaRepository.findByDescricao("CANCELADA")
                    .orElseThrow(() -> new IllegalArgumentException("Status de reserva 'CANCELADA' não encontrado"));

            reserva.setStatus(statusCancelada);
            reservaRepository.save(reserva);
            return true;
        } else {
            return false;
        }
    }
}