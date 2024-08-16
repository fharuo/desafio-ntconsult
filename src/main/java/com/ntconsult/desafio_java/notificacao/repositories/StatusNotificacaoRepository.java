package com.ntconsult.desafio_java.notificacao.repositories;

import com.ntconsult.desafio_java.notificacao.models.StatusNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusNotificacaoRepository extends JpaRepository<StatusNotificacao, Long> {
    Optional<StatusNotificacao> findByDescricao(String descricao);
}
