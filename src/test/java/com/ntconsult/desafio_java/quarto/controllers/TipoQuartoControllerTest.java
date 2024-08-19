package com.ntconsult.desafio_java.quarto.controllers;

import com.ntconsult.desafio_java.quarto.models.TipoQuarto;
import com.ntconsult.desafio_java.quarto.services.TipoQuartoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TipoQuartoController.class)
public class TipoQuartoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoQuartoService tipoQuartoService;

    @Test
    public void deveCriarTipoQuarto() throws Exception {
        TipoQuarto tipoQuarto = new TipoQuarto();
        tipoQuarto.setId(1L);
        tipoQuarto.setDescricao("Suite");

        Mockito.when(tipoQuartoService.criarTipoQuarto(Mockito.any(TipoQuarto.class)))
                .thenReturn(tipoQuarto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tipos-quarto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\": \"Suite\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.descricao").value("Suite"));
    }

    @Test
    public void deveListarTiposQuarto() throws Exception {
        TipoQuarto tipoQuarto1 = new TipoQuarto();
        tipoQuarto1.setId(1L);
        tipoQuarto1.setDescricao("Suite");

        TipoQuarto tipoQuarto2 = new TipoQuarto();
        tipoQuarto2.setId(2L);
        tipoQuarto2.setDescricao("Double");

        Mockito.when(tipoQuartoService.listarTiposQuarto())
                .thenReturn(Arrays.asList(tipoQuarto1, tipoQuarto2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tipos-quarto")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].descricao").value("Suite"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].descricao").value("Double"));
    }

    @Test
    public void deveObterTipoQuartoPorId() throws Exception {
        TipoQuarto tipoQuarto = new TipoQuarto();
        tipoQuarto.setId(1L);
        tipoQuarto.setDescricao("Suite");

        Mockito.when(tipoQuartoService.obterTipoQuartoPorId(1L))
                .thenReturn(Optional.of(tipoQuarto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tipos-quarto/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.descricao").value("Suite"));
    }

    @Test
    public void deveDeletarTipoQuarto() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tipos-quarto/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(tipoQuartoService, Mockito.times(1)).deletarTipoQuarto(1L);
    }
}