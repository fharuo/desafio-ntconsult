package com.ntconsult.desafio_java.reserva.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaResponseDTO {

    private Long reservaId;

    private Long quartoId;

    private LocalDate dataCheckin;

    private LocalDate dataCheckout;

    private String nomeCliente;

    private String contatoCliente;

    private String statusReserva;
}
