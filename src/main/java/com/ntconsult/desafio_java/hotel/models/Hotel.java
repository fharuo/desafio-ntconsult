package com.ntconsult.desafio_java.hotel.models;

import com.ntconsult.desafio_java.quarto.models.Quarto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String localizacao;

    @ElementCollection
    @CollectionTable(name = "hotel_comodidades", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "comodidade")
    private List<String> comodidades;

    private Double avaliacaoMedia;

    private Integer numeroAvaliacoes;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Quarto> quartos = new ArrayList<>();

}
