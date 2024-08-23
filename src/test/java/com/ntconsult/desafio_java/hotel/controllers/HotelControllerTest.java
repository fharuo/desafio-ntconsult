package com.ntconsult.desafio_java.hotel.controllers;

import com.ntconsult.desafio_java.hotel.dtos.ComparacaoDTO;
import com.ntconsult.desafio_java.hotel.dtos.ComparacaoPrecoDTO;
import com.ntconsult.desafio_java.hotel.dtos.HotelDTO;
import com.ntconsult.desafio_java.hotel.services.HotelComparisonService;
import com.ntconsult.desafio_java.hotel.services.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class HotelControllerTest {

    @InjectMocks
    private HotelController hotelController;

    @Mock
    private HotelService hotelService;

    @Mock
    private HotelComparisonService hotelComparisonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarHoteis_Sucesso() {
        List<HotelDTO> hotelList = Collections.singletonList(new HotelDTO());
        when(hotelService.listarHoteis()).thenReturn(hotelList);

        ResponseEntity<List<HotelDTO>> response = hotelController.listarHoteis();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hotelList, response.getBody());
    }

    @Test
    void pesquisarHoteis_Sucesso() {
        String localizacao = "São Paulo";
        LocalDate checkin = LocalDate.now();
        LocalDate checkout = LocalDate.now().plusDays(2);
        int numeroQuartos = 2;
        int numeroHospedes = 4;

        List<HotelDTO> hotelList = Collections.singletonList(new HotelDTO());
        when(hotelService.pesquisarHoteis(localizacao, checkin, checkout, numeroQuartos, numeroHospedes)).thenReturn(hotelList);

        ResponseEntity<List<HotelDTO>> response = hotelController.pesquisarHoteis(localizacao, checkin, checkout, numeroQuartos, numeroHospedes);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hotelList, response.getBody());
    }

    @Test
    void compararHoteisPorAvaliacao_Sucesso() {
        Long primeiroHotelId = 1L;
        Long segundoHotelId = 2L;

        List<ComparacaoDTO> comparacaoList = Collections.singletonList(new ComparacaoDTO());
        when(hotelComparisonService.compararHoteisPorAvaliacao(primeiroHotelId, segundoHotelId)).thenReturn(comparacaoList);

        ResponseEntity<List<ComparacaoDTO>> response = hotelController.compararHoteisPorAvaliacao(primeiroHotelId, segundoHotelId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comparacaoList, response.getBody());
    }

    @Test
    void compararHoteisPorLocalizacao_Sucesso() {
        Long primeiroHotelId = 1L;
        Long segundoHotelId = 2L;

        List<ComparacaoDTO> comparacaoList = Collections.singletonList(new ComparacaoDTO());
        when(hotelComparisonService.compararHoteisPorLocalizacao(primeiroHotelId, segundoHotelId)).thenReturn(comparacaoList);

        ResponseEntity<List<ComparacaoDTO>> response = hotelController.compararHoteisPorLocalizacao(primeiroHotelId, segundoHotelId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comparacaoList, response.getBody());
    }

    @Test
    void compararHoteisPorComodidades_Sucesso() {
        Long primeiroHotelId = 1L;
        Long segundoHotelId = 2L;

        List<ComparacaoDTO> comparacaoList = Collections.singletonList(new ComparacaoDTO());
        when(hotelComparisonService.compararHoteisPorComodidades(primeiroHotelId, segundoHotelId)).thenReturn(comparacaoList);

        ResponseEntity<List<ComparacaoDTO>> response = hotelController.compararHoteisPorComodidades(primeiroHotelId, segundoHotelId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comparacaoList, response.getBody());
    }

    @Test
    void compararHoteisPorPreco_Sucesso() {
        Long primeiroHotelId = 1L;
        Long segundoHotelId = 2L;

        List<ComparacaoPrecoDTO> comparacaoList = Collections.singletonList(new ComparacaoPrecoDTO());
        when(hotelComparisonService.compararHoteisPorPreco(primeiroHotelId, segundoHotelId)).thenReturn(comparacaoList);

        ResponseEntity<List<ComparacaoPrecoDTO>> response = hotelController.compararHoteisPorPreco(primeiroHotelId, segundoHotelId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comparacaoList, response.getBody());
    }
}
