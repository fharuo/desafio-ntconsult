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

    @Query(value = "SELECT h.* FROM hotel h " +
            "JOIN quarto q ON h.id = q.hotel_id " +
            "LEFT JOIN reserva r ON q.id = r.quarto_id " +
            "AND (r.data_checkin <= :dataCheckout AND r.data_checkout >= :dataCheckin) " +
            "WHERE h.localizacao LIKE %:localizacao% " +
            "AND q.disponivel = true " +
            "AND q.capacidade >= :numeroHospedes " +
            "AND r.id IS NULL " +
            "GROUP BY h.id " +
            "HAVING COUNT(q.id) >= :numeroQuartos", nativeQuery = true)
    List<Hotel> findByCriteria(String localizacao, LocalDate dataCheckin, LocalDate dataCheckout, int numeroQuartos, int numeroHospedes);

}
