package com.ntconsult.desafio_java.reserva.repositories;

import com.ntconsult.desafio_java.reserva.models.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusReservaRepository extends JpaRepository<StatusReserva, Long> {
    Optional<StatusReserva> findByDescricao(String descricao);
}