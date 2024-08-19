package com.ntconsult.desafio_java.hotel.repositories;

import com.ntconsult.desafio_java.hotel.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByLocalizacaoAndPrecoNoiteBetween(String localizacao, BigDecimal precoMin, BigDecimal precoMax);

    @Query("SELECT h FROM Hotel h WHERE " +
            "(:localizacao IS NULL OR h.localizacao LIKE %:localizacao%) AND " +
            "(:dataCheckin IS NULL OR h.id NOT IN (SELECT r.hotel.id FROM Reserva r WHERE :dataCheckin BETWEEN r.dataCheckin AND r.dataCheckout OR :dataCheckout BETWEEN r.dataCheckin AND r.dataCheckout)) AND " +
            "(:numeroQuartos IS NULL OR :numeroHospedes IS NULL OR h.id IN (SELECT q.hotel.id FROM Quarto q WHERE q.disponivel = true))")
    List<Hotel> findByCriteria(String localizacao, LocalDate dataCheckin, LocalDate dataCheckout, int numeroQuartos, int numeroHospedes);

}
