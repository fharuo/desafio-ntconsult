package com.ntconsult.desafio_java.quarto.repositories;

import com.ntconsult.desafio_java.quarto.models.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    List<Quarto> findByHotelIdAndDisponivel(Long hotelId, Boolean disponivel);
}
