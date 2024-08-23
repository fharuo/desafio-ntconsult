package com.ntconsult.desafio_java.reserva.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.ntconsult.desafio_java.reserva.dtos.ReservaRequestDTO;
import com.ntconsult.desafio_java.reserva.dtos.ReservaResponseDTO;
import com.ntconsult.desafio_java.reserva.services.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ReservaControllerTest {

    @InjectMocks
    private ReservaController reservaController;

    @Mock
    private ReservaService reservaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarReserva_Sucesso() {
        ReservaRequestDTO requestDTO = new ReservaRequestDTO();
        ReservaResponseDTO responseDTO = new ReservaResponseDTO();
        when(reservaService.criarReserva(any(ReservaRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<ReservaResponseDTO> responseEntity = reservaController.criarReserva(requestDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
        verify(reservaService, times(1)).criarReserva(any(ReservaRequestDTO.class));
    }

    @Test
    void criarReserva_Conflito() {
        ReservaRequestDTO requestDTO = new ReservaRequestDTO();
        when(reservaService.criarReserva(any(ReservaRequestDTO.class))).thenReturn(null);

        ResponseEntity<ReservaResponseDTO> responseEntity = reservaController.criarReserva(requestDTO);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        verify(reservaService, times(1)).criarReserva(any(ReservaRequestDTO.class));
    }

    @Test
    void cancelarReserva_Sucesso() {
        when(reservaService.cancelarReserva(anyLong())).thenReturn(true);

        ResponseEntity<Void> responseEntity = reservaController.cancelarReserva(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(reservaService, times(1)).cancelarReserva(anyLong());
    }

    @Test
    void cancelarReserva_NaoEncontrado() {
        when(reservaService.cancelarReserva(anyLong())).thenReturn(false);

        ResponseEntity<Void> responseEntity = reservaController.cancelarReserva(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(reservaService, times(1)).cancelarReserva(anyLong());
    }

    @Test
    void processarCheckin_Sucesso() {
        when(reservaService.processarCheckin(anyLong())).thenReturn(true);

        ResponseEntity<Void> responseEntity = reservaController.processarCheckin(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(reservaService, times(1)).processarCheckin(anyLong());
    }

    @Test
    void processarCheckin_NaoEncontrado() {
        when(reservaService.processarCheckin(anyLong())).thenReturn(false);

        ResponseEntity<Void> responseEntity = reservaController.processarCheckin(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(reservaService, times(1)).processarCheckin(anyLong());
    }

    @Test
    void processarCheckout_Sucesso() {
        when(reservaService.processarCheckout(anyLong())).thenReturn(true);

        ResponseEntity<Void> responseEntity = reservaController.processarCheckout(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(reservaService, times(1)).processarCheckout(anyLong());
    }

    @Test
    void processarCheckout_NaoEncontrado() {
        when(reservaService.processarCheckout(anyLong())).thenReturn(false);

        ResponseEntity<Void> responseEntity = reservaController.processarCheckout(1L);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(reservaService, times(1)).processarCheckout(anyLong());
    }
}