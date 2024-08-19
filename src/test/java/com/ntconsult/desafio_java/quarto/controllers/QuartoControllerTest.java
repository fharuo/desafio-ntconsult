package com.ntconsult.desafio_java.quarto.controllers;

import com.ntconsult.desafio_java.quarto.models.Quarto;
import com.ntconsult.desafio_java.quarto.services.QuartoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuartoController.class)
public class QuartoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuartoService quartoService;

    @Test
    public void deveCriarQuarto() throws Exception {
        Quarto quarto = new Quarto();
        quarto.setId(1L);
        quarto.setNumero("101");
        quarto.setPrecoNoite(BigDecimal.valueOf(150.00));
        quarto.setDisponivel(true);

        Mockito.when(quartoService.criarQuarto(Mockito.any(Quarto.class)))
                .thenReturn(quarto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/quartos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numero\": \"101\", \"precoNoite\": 150.00, \"disponivel\": true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.numero").value("101"))
                .andExpect(jsonPath("$.precoNoite").value(150.00))
                .andExpect(jsonPath("$.disponivel").value(true));
    }

    @Test
    public void deveListarQuartosPorHotel() throws Exception {
        Quarto quarto1 = new Quarto();
        quarto1.setId(1L);
        quarto1.setNumero("101");
        quarto1.setPrecoNoite(BigDecimal.valueOf(150.00));
        quarto1.setDisponivel(true);

        Quarto quarto2 = new Quarto();
        quarto2.setId(2L);
        quarto2.setNumero("102");
        quarto2.setPrecoNoite(BigDecimal.valueOf(200.00));
        quarto2.setDisponivel(true);

        Mockito.when(quartoService.listarQuartosPorHotel(1L))
                .thenReturn(Arrays.asList(quarto1, quarto2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/quartos/hotel/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].numero").value("101"))
                .andExpect(jsonPath("$[0].precoNoite").value(150.00))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].numero").value("102"))
                .andExpect(jsonPath("$[1].precoNoite").value(200.00));
    }

    @Test
    public void deveObterQuartoPorId() throws Exception {
        Quarto quarto = new Quarto();
        quarto.setId(1L);
        quarto.setNumero("101");
        quarto.setPrecoNoite(BigDecimal.valueOf(150.00));
        quarto.setDisponivel(true);

        Mockito.when(quartoService.obterQuartoPorId(1L))
                .thenReturn(Optional.of(quarto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/quartos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.numero").value("101"))
                .andExpect(jsonPath("$.precoNoite").value(150.00))
                .andExpect(jsonPath("$.disponivel").value(true));
    }

    @Test
    public void deveDeletarQuarto() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/quartos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(quartoService, Mockito.times(1)).deletarQuarto(1L);
    }
}