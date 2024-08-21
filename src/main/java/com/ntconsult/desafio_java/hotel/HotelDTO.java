package com.ntconsult.desafio_java.hotel;

import com.ntconsult.desafio_java.hotel.models.Hotel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class HotelDTO {

    private Long id;

    private String nome;

    private String localizacao;

    private List<String> comodidades;

    private Double avaliacaoMedia;

    private Integer numeroAvaliacoes;

    public HotelDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.nome = hotel.getNome();
        this.localizacao = hotel.getLocalizacao();
    }
}
