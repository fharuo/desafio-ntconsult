package com.ntconsult.desafio_java.reserva.repositories;

import com.ntconsult.desafio_java.reserva.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByHotelIdAndDataCheckinBetween(Long hotelId, LocalDate startDate, LocalDate endDate);
}
