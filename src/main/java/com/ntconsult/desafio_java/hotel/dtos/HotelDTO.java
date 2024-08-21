package com.ntconsult.desafio_java.hotel.dtos;

import com.ntconsult.desafio_java.hotel.models.Hotel;
import com.ntconsult.desafio_java.quarto.dtos.QuartoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String localizacao;

    private List<String> comodidades;

    private Double avaliacaoMedia;

    private Integer numeroAvaliacoes;

    private List<QuartoDTO> quartos;

    public HotelDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.nome = hotel.getNome();
        this.localizacao = hotel.getLocalizacao();
        this.comodidades = hotel.getComodidades();
        this.avaliacaoMedia = hotel.getAvaliacaoMedia();
        this.numeroAvaliacoes = hotel.getNumeroAvaliacoes();
        this.quartos =hotel.getQuartos().stream().map(QuartoDTO::new).toList();
    }
}
