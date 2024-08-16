package com.ntconsult.desafio_java.notificacao.repositories;

import com.ntconsult.desafio_java.notificacao.models.TipoNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoNotificacaoRepository extends JpaRepository<TipoNotificacao, Long> {
    Optional<TipoNotificacao> findByDescricao(String descricao);
}
