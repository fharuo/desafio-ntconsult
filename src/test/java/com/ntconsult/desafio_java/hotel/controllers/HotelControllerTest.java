package com.ntconsult.desafio_java.hotel.controllers;

import com.ntconsult.desafio_java.hotel.services.HotelComparisonService;
import com.ntconsult.desafio_java.hotel.services.HotelService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    @InjectMocks
    private HotelController hotelController;

    @Mock
    private HotelService hotelService;

    @Mock
    private HotelComparisonService hotelComparisonService;

    @Mock
    private MockMvc mockMvc;

    @Test
    public void testCompararHoteisPorPreco() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(hotelService.pesquisarHoteis(null, null, null, 0, 0)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/hoteis/comparar/preco")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCompararHoteisPorAvaliacao() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(hotelService.pesquisarHoteis(null, null, null, 0, 0)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/hoteis/comparar/avaliacao")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Adicionar outros testes conforme necessário.
}