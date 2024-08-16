package com.ntconsult.desafio_java.notificacao.repositories;

import com.ntconsult.desafio_java.notificacao.models.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByReservaId(Long reservaId);
}