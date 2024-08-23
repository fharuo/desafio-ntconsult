package com.ntconsult.desafio_java.reserva.services;

import com.ntconsult.desafio_java.dtos.NotificationDTO;
import com.ntconsult.desafio_java.exceptions.NotFoundException;
import com.ntconsult.desafio_java.hotel.models.Hotel;
import com.ntconsult.desafio_java.hotel.repositories.HotelRepository;
import com.ntconsult.desafio_java.notificacao.services.NotificacaoService;
import com.ntconsult.desafio_java.quarto.models.Quarto;
import com.ntconsult.desafio_java.quarto.repositories.QuartoRepository;
import com.ntconsult.desafio_java.reserva.dtos.ReservaRequestDTO;
import com.ntconsult.desafio_java.reserva.dtos.ReservaResponseDTO;
import com.ntconsult.desafio_java.reserva.models.Reserva;
import com.ntconsult.desafio_java.reserva.models.StatusReserva;
import com.ntconsult.desafio_java.reserva.repositories.ReservaRepository;
import com.ntconsult.desafio_java.reserva.repositories.StatusReservaRepository;
import com.ntconsult.desafio_java.reserva.validators.ReservaValidador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservaServiceTest {

    @InjectMocks
    private ReservaService reservaService;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private QuartoRepository quartoRepository;

    @Mock
    private StatusReservaRepository statusReservaRepository;

    @Mock
    private ReservaValidador reservaValidador;

    @Mock
    private NotificacaoService notificacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarReserva_Sucesso() {
        ReservaRequestDTO requestDTO = new ReservaRequestDTO();
        requestDTO.setHotelId(1L);
        requestDTO.setQuartoId(1L);
        requestDTO.setNomeCliente("Cliente Teste");



        Hotel hotel = new Hotel();
        Quarto quarto = new Quarto();
        StatusReserva statusConfirmada = new StatusReserva();
        statusConfirmada.setDescricao("CONFIRMADA");
        Reserva reserva = new Reserva();
        reserva.setHotel(hotel);
        reserva.setQuarto(quarto);
        reserva.setPax(1L);
        reserva.setStatus(new StatusReserva());
        reserva.setNomeCliente("John Doe");
        reserva.setContatoCliente("+55 11 9492029122");
        reserva.setDataCheckin(LocalDate.now());
        reserva.setDataCheckin(LocalDate.now().plusDays(1));

        when(hotelRepository.findById(requestDTO.getHotelId())).thenReturn(Optional.of(hotel));
        when(quartoRepository.findById(requestDTO.getQuartoId())).thenReturn(Optional.of(quarto));
        when(statusReservaRepository.findByDescricao("CONFIRMADA")).thenReturn(Optional.of(statusConfirmada));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        ReservaResponseDTO responseDTO = reservaService.criarReserva(requestDTO);

        assertNotNull(responseDTO);
        verify(notificacaoService, times(1)).enviarNotificacao(any(NotificationDTO.class));
    }

    @Test
    void criarReserva_StatusReservaNaoEncontrado() {
        ReservaRequestDTO requestDTO = new ReservaRequestDTO();
        requestDTO.setHotelId(1L);
        requestDTO.setQuartoId(1L);

        Hotel hotel = new Hotel();
        Quarto quarto = new Quarto();

        when(hotelRepository.findById(requestDTO.getHotelId())).thenReturn(Optional.of(hotel));
        when(quartoRepository.findById(requestDTO.getQuartoId())).thenReturn(Optional.of(quarto));
        when(statusReservaRepository.findByDescricao("CONFIRMADA")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> reservaService.criarReserva(requestDTO));
        verify(reservaRepository, never()).save(any(Reserva.class));
    }

    @Test
    void cancelarReserva_Sucesso() {
        Reserva reserva = new Reserva();
        StatusReserva statusCancelada = new StatusReserva();
        statusCancelada.setDescricao("CANCELADA");

        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(statusReservaRepository.findByDescricao("CANCELADA")).thenReturn(Optional.of(statusCancelada));

        boolean resultado = reservaService.cancelarReserva(1L);

        assertTrue(resultado);
        assertEquals(statusCancelada, reserva.getStatus());
        verify(notificacaoService, times(1)).enviarNotificacao(any(NotificationDTO.class));
    }

    @Test
    void cancelarReserva_ReservaNaoEncontrada() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.empty());

        boolean resultado = reservaService.cancelarReserva(1L);

        assertFalse(resultado);
        verify(reservaRepository, never()).save(any(Reserva.class));
    }

    @Test
    void processarCheckin_Sucesso() {
        Reserva reserva = new Reserva();
        StatusReserva statusCheckin = new StatusReserva();
        statusCheckin.setDescricao("CHECKED_IN");

        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(statusReservaRepository.findByDescricao("CHECKED_IN")).thenReturn(Optional.of(statusCheckin));

        boolean resultado = reservaService.processarCheckin(1L);

        assertTrue(resultado);
        assertEquals(statusCheckin, reserva.getStatus());
        verify(notificacaoService, times(1)).enviarNotificacao(any(NotificationDTO.class));
    }

    @Test
    void processarCheckin_ReservaNaoEncontrada() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.empty());

        boolean resultado = reservaService.processarCheckin(1L);

        assertFalse(resultado);
        verify(reservaRepository, never()).save(any(Reserva.class));
    }

    @Test
    void processarCheckout_Sucesso() {
        Reserva reserva = new Reserva();
        StatusReserva statusCheckout = new StatusReserva();
        statusCheckout.setDescricao("CHECKED_OUT");

        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(statusReservaRepository.findByDescricao("CHECKED_OUT")).thenReturn(Optional.of(statusCheckout));

        boolean resultado = reservaService.processarCheckout(1L);

        assertTrue(resultado);
        assertEquals(statusCheckout, reserva.getStatus());
        verify(notificacaoService, times(1)).enviarNotificacao(any(NotificationDTO.class));
    }

    @Test
    void processarCheckout_ReservaNaoEncontrada() {
        when(reservaRepository.findById(1L)).thenReturn(Optional.empty());

        boolean resultado = reservaService.processarCheckout(1L);

        assertFalse(resultado);
        verify(reservaRepository, never()).save(any(Reserva.class));
    }
}
