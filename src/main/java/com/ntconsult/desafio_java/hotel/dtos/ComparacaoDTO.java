package com.ntconsult.desafio_java.hotel.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComparacaoDTO {

    private String nomeHotel;

    private String criterio;

    private List<String> resultado = new ArrayList<>();
}
