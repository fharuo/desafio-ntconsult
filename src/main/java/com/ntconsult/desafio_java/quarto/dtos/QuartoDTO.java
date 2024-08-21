package com.ntconsult.desafio_java.quarto.dtos;

import com.ntconsult.desafio_java.quarto.models.Quarto;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuartoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String numero;

    private BigDecimal precoNoite;

    private Boolean disponivel;

    private Integer capacidade;

    public QuartoDTO(Quarto quarto) {
        this.numero = quarto.getNumero();
        this.precoNoite = quarto.getPrecoNoite();
        this.disponivel = quarto.getDisponivel();
        this.capacidade = quarto.getCapacidade();
    }
}
