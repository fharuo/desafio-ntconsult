package com.ntconsult.desafio_java.quarto.repositories;

import com.ntconsult.desafio_java.quarto.models.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    List<Quarto> findByHotelIdAndDisponivel(Long hotelId, Boolean disponivel);

    List<Quarto> findByHotelId(Long hotelId);

    boolean existsById(Long id);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN false ELSE true END FROM Reserva r WHERE r.quarto.id = :quartoId AND " +
            "(r.dataCheckin <= :dataCheckout AND r.dataCheckout >= :dataCheckin)")
    boolean isQuartoDisponivel(Long quartoId, LocalDate dataCheckin, LocalDate dataCheckout);
}
