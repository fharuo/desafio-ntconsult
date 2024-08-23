package com.ntconsult.desafio_java.hotel.services;

import com.ntconsult.desafio_java.exceptions.NotFoundException;
import com.ntconsult.desafio_java.hotel.dtos.ComparacaoDTO;
import com.ntconsult.desafio_java.hotel.dtos.ComparacaoPrecoDTO;
import com.ntconsult.desafio_java.hotel.models.Hotel;
import com.ntconsult.desafio_java.hotel.repositories.HotelRepository;
import com.ntconsult.desafio_java.quarto.models.Quarto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class HotelComparisonServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelComparisonService hotelComparisonService;

    private Hotel hotel1;
    private Hotel hotel2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        hotel1 = new Hotel();
        hotel1.setId(1L);
        hotel1.setNome("Hotel 1");
        hotel1.setAvaliacaoMedia(4.5);
        hotel1.setLocalizacao("Localização 1");
        hotel1.setComodidades(List.of("Comodidade 1", "Comodidade 2"));

        hotel2 = new Hotel();
        hotel2.setId(2L);
        hotel2.setNome("Hotel 2");
        hotel2.setAvaliacaoMedia(4.0);
        hotel2.setLocalizacao("Localização 2");
        hotel2.setComodidades(List.of("Comodidade 1, Comodidade 2"));

        Quarto quarto1 = new Quarto();
        quarto1.setCapacidade(2);
        quarto1.setPrecoNoite(BigDecimal.valueOf(200));

        Quarto quarto2 = new Quarto();
        quarto2.setCapacidade(3);
        quarto2.setPrecoNoite(BigDecimal.valueOf(300));

        hotel1.setQuartos(List.of(quarto1));
        hotel2.setQuartos(List.of(quarto2));
    }

    @Test
    void compararHoteisPorAvaliacao() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel1));
        when(hotelRepository.findById(2L)).thenReturn(Optional.of(hotel2));

        List<ComparacaoDTO> resultado = hotelComparisonService.compararHoteisPorAvaliacao(1L, 2L);

        assertEquals(2, resultado.size());
        assertEquals("Hotel 1", resultado.get(0).getNomeHotel());
        assertEquals("4.5", resultado.get(0).getResultado().get(0));
        assertEquals("Hotel 2", resultado.get(1).getNomeHotel());
        assertEquals("4.0", resultado.get(1).getResultado().get(0));
    }

    @Test
    void compararHoteisPorLocalizacao() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel1));
        when(hotelRepository.findById(2L)).thenReturn(Optional.of(hotel2));

        List<ComparacaoDTO> resultado = hotelComparisonService.compararHoteisPorLocalizacao(1L, 2L);

        assertEquals(2, resultado.size());
        assertEquals("Localização 1", resultado.get(0).getResultado().get(0));
        assertEquals("Localização 2", resultado.get(1).getResultado().get(0));
    }

    @Test
    void compararHoteisPorComodidades() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel1));
        when(hotelRepository.findById(2L)).thenReturn(Optional.of(hotel2));

        List<ComparacaoDTO> resultado = hotelComparisonService.compararHoteisPorComodidades(1L, 2L);

        assertEquals(2, resultado.size());
    }

    @Test
    void compararHoteisPorPreco() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel1));
        when(hotelRepository.findById(2L)).thenReturn(Optional.of(hotel2));

        List<ComparacaoPrecoDTO> resultado = hotelComparisonService.compararHoteisPorPreco(1L, 2L);

        assertEquals(2, resultado.size());
        assertEquals("200", resultado.get(0).getResultado().get(0).get("Preço"));
        assertEquals("2", resultado.get(0).getResultado().get(0).get("Capacidade"));
        assertEquals("300", resultado.get(1).getResultado().get(0).get("Preço"));
        assertEquals("3", resultado.get(1).getResultado().get(0).get("Capacidade"));
    }

    @Test
    void compararHoteisNotFound() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> hotelComparisonService.compararHoteisPorAvaliacao(1L, 2L));
    }
}
