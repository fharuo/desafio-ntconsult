package com.ntconsult.desafio_java.reserva.dtos;

import com.ntconsult.desafio_java.reserva.models.Reserva;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaResponseDTO {

    private Long reservaId;

    private String hotel;

    private String quartoNumero;

    private LocalDate dataCheckin;

    private LocalDate dataCheckout;

    private String nomeCliente;

    private Long pax;

    private String contatoCliente;

    private String statusReserva;

    public ReservaResponseDTO(Reserva reserva) {
        this.reservaId = reserva.getId();
        this.hotel = reserva.getHotel().getNome();
        this.quartoNumero = reserva.getQuarto().getNumero();
        this.dataCheckin = reserva.getDataCheckin();
        this.dataCheckout = reserva.getDataCheckout();
        this.nomeCliente = reserva.getNomeCliente();
        this.pax = reserva.getPax();
        this.contatoCliente = reserva.getContatoCliente();
        this.statusReserva = reserva.getStatus().getDescricao();
    }
}
