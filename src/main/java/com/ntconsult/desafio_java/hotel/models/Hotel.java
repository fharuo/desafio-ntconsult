package com.ntconsult.desafio_java.hotel.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Data
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String localizacao;

    private BigDecimal precoNoite;

    @ElementCollection
    private List<String> comodidades;

    private Double avaliacaoMedia;

    private Integer numeroAvaliacoes;

}
