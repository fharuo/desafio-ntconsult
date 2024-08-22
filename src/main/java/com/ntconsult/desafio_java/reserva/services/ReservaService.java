package com.ntconsult.desafio_java.reserva.services;

import com.ntconsult.desafio_java.dtos.NotificationDTO;
import com.ntconsult.desafio_java.exceptions.NotFoundException;
import com.ntconsult.desafio_java.hotel.repositories.HotelRepository;
import com.ntconsult.desafio_java.notificacao.services.NotificacaoService;
import com.ntconsult.desafio_java.quarto.repositories.QuartoRepository;
import com.ntconsult.desafio_java.reserva.dtos.ReservaRequestDTO;
import com.ntconsult.desafio_java.reserva.dtos.ReservaResponseDTO;
import com.ntconsult.desafio_java.reserva.models.Reserva;
import com.ntconsult.desafio_java.reserva.models.StatusReserva;
import com.ntconsult.desafio_java.reserva.repositories.ReservaRepository;
import com.ntconsult.desafio_java.reserva.repositories.StatusReservaRepository;
import com.ntconsult.desafio_java.reserva.validators.ReservaValidador;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReservaService {

    private final HotelRepository hotelRepository;

    private final ReservaRepository reservaRepository;

    private final QuartoRepository quartoRepository;

    private final StatusReservaRepository statusReservaRepository;

    private final ReservaValidador reservaValidador;

    private final NotificacaoService notificacaoService;

    public ReservaResponseDTO criarReserva(ReservaRequestDTO dto) {
        reservaValidador.executar(dto);

        Reserva reserva = new Reserva();
        reserva.setHotel(hotelRepository.findById(dto.getHotelId()).get());
        reserva.setQuarto(quartoRepository.findById(dto.getQuartoId()).get());
        reserva.setDataCheckin(dto.getDataCheckin());
        reserva.setDataCheckout(dto.getDataCheckout());
        reserva.setNomeCliente(dto.getNomeCliente());
        reserva.setPax(dto.getPax());
        reserva.setContatoCliente(dto.getContatoCliente());
        reserva.setDetalhesPagamento(dto.getDetalhesPagamento());

        StatusReserva statusConfirmado = statusReservaRepository.findByDescricao("CONFIRMADA").orElseThrow(() -> new NotFoundException("Status de reserva não encontrado"));
        reserva.setStatus(statusConfirmado);

        Reserva reservaConfirmada = reservaRepository.save(reserva);

        NotificationDTO notificationDTO = new NotificationDTO(reservaConfirmada.getId(), "CONFIRMACAO_RESERVA", "ENVIADA", LocalDateTime.now().toString());
        notificacaoService.enviarNotificacao(notificationDTO);

        return new ReservaResponseDTO(reservaConfirmada);
    }

    public boolean cancelarReserva(Long id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            StatusReserva statusCancelada = statusReservaRepository.findByDescricao("CANCELADA")
                    .orElseThrow(() -> new NotFoundException("Status de reserva 'CANCELADA' não encontrado"));

            reserva.setStatus(statusCancelada);
            reservaRepository.save(reserva);

            NotificationDTO notificationDTO = new NotificationDTO(reserva.getId(), "CANCELAMENTO_RESERVA", "ENVIADA", LocalDateTime.now().toString());
            notificacaoService.enviarNotificacao(notificationDTO);

            return true;
        } else {
            return false;
        }
    }

    public boolean processarCheckin(Long reservaId) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(reservaId);

        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            StatusReserva statusCheckin = statusReservaRepository.findByDescricao("CHECKED_IN")
                    .orElseThrow(() -> new NotFoundException("Status de reserva 'CHECKED_IN' não encontrado"));

            reserva.setStatus(statusCheckin);
            reservaRepository.save(reserva);

            NotificationDTO notificationDTO = new NotificationDTO(reserva.getId(), "CHECKIN_REALIZADO", "ENVIADA", LocalDateTime.now().toString());
            notificacaoService.enviarNotificacao(notificationDTO);

            return true;
        } else {
            return false;
        }
    }

    public boolean processarCheckout(Long reservaId) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(reservaId);

        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();
            StatusReserva statusCheckout = statusReservaRepository.findByDescricao("CHECKED_OUT")
                    .orElseThrow(() -> new NotFoundException("Status de reserva 'CHECKED_OUT' não encontrado"));

            reserva.setStatus(statusCheckout);
            reservaRepository.save(reserva);

            NotificationDTO notificationDTO = new NotificationDTO(reserva.getId(), "CHECKOUT_REALIZADO", "ENVIADA", LocalDateTime.now().toString());
            notificacaoService.enviarNotificacao(notificationDTO);

            return true;
        } else {
            return false;
        }
    }
}