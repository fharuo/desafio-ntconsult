package com.ntconsult.desafio_java.hotel.services;

import com.ntconsult.desafio_java.hotel.dtos.HotelDTO;
import com.ntconsult.desafio_java.hotel.models.Hotel;
import com.ntconsult.desafio_java.hotel.repositories.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class HotelServiceTest {

    @InjectMocks
    private HotelService hotelService;

    @Mock
    private HotelRepository hotelRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarHoteis_Sucesso() {
        // Mock data
        Hotel hotel = new Hotel();
        hotel.setNome("Hotel Teste");

        List<Hotel> hoteis = Collections.singletonList(hotel);
        when(hotelRepository.findAll()).thenReturn(hoteis);

        // Execute the service method
        List<HotelDTO> result = hotelService.listarHoteis();

        // Assertions
        assertEquals(1, result.size());
        assertEquals("Hotel Teste", result.get(0).getNome());
    }

    @Test
    void pesquisarHoteis_Sucesso() {
        // Mock data
        String localizacao = "São Paulo";
        LocalDate dataCheckin = LocalDate.now();
        LocalDate dataCheckout = LocalDate.now().plusDays(2);
        int numeroQuartos = 2;
        int numeroHospedes = 4;

        Hotel hotel = new Hotel();
        hotel.setNome("Hotel Teste");
        hotel.setLocalizacao(localizacao);

        List<Hotel> hoteis = Collections.singletonList(hotel);
        when(hotelRepository.findByCriteria(localizacao, dataCheckin, dataCheckout, numeroQuartos, numeroHospedes)).thenReturn(hoteis);

        // Execute the service method
        List<HotelDTO> result = hotelService.pesquisarHoteis(localizacao, dataCheckin, dataCheckout, numeroQuartos, numeroHospedes);

        // Assertions
        assertEquals(1, result.size());
        assertEquals("Hotel Teste", result.get(0).getNome());
        assertEquals(localizacao, result.get(0).getLocalizacao());
    }
}
