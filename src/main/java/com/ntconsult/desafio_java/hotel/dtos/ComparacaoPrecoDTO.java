package com.ntconsult.desafio_java.hotel.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ComparacaoPrecoDTO {

    private String nomeHotel;

    private String criterio;

    private List<Map<String, String>> resultado = new ArrayList<>();
}
