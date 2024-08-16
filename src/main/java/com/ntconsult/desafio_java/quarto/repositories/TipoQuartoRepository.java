package com.ntconsult.desafio_java.quarto.repositories;

import com.ntconsult.desafio_java.quarto.models.TipoQuarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoQuartoRepository extends JpaRepository<TipoQuarto, Long> {
    Optional<TipoQuarto> findByDescricao(String descricao);
}