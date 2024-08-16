package com.ntconsult.desafio_java.reserva.models;

import com.ntconsult.desafio_java.hotel.models.Hotel;
import com.ntconsult.desafio_java.quarto.models.Quarto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataCheckin;

    private LocalDate dataCheckout;

    private String nomeCliente;

    private String contatoCliente;

    @Lob
    private String detalhesPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quarto_id", nullable = false)
    private Quarto quarto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusReserva status;
}

