package com.ntconsult.desafio_java.reserva.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaRequestDTO {

    private Long hotelId;

    private Long quartoId;

    private LocalDate dataCheckin;

    private LocalDate dataCheckout;

    private String nomeCliente;

    private Long pax;

    private String contatoCliente;

    private String detalhesPagamento;
}
