package com.ntconsult.desafio_java.notificacao.services;

import com.ntconsult.desafio_java.notificacao.models.Notificacao;
import com.ntconsult.desafio_java.notificacao.models.StatusNotificacao;
import com.ntconsult.desafio_java.notificacao.models.TipoNotificacao;
import com.ntconsult.desafio_java.notificacao.repositories.NotificacaoRepository;
import com.ntconsult.desafio_java.notificacao.repositories.StatusNotificacaoRepository;
import com.ntconsult.desafio_java.notificacao.repositories.TipoNotificacaoRepository;
import com.ntconsult.desafio_java.reserva.models.Reserva;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    private final StatusNotificacaoRepository statusNotificacaoRepository;

    private final TipoNotificacaoRepository tipoNotificacaoRepository;

    public void enviarNotificacao(Reserva reserva, String tipoDescricao) {
        TipoNotificacao tipoNotificacao = tipoNotificacaoRepository.findByDescricao(tipoDescricao)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de Notificação não encontrado"));

        StatusNotificacao statusNotificacao = statusNotificacaoRepository.findByDescricao("ENVIADA")
                .orElseThrow(() -> new IllegalArgumentException("Status de Notificação não encontrado"));

        Notificacao notificacao = new Notificacao();
        notificacao.setReserva(reserva);
        notificacao.setTipo(tipoNotificacao);
        notificacao.setStatus(statusNotificacao);

        notificacaoRepository.save(notificacao);

        // Aqui você pode integrar com um sistema de envio de emails ou mensagens, etc.
        System.out.println("Notificação enviada: " + tipoDescricao + " para a reserva " + reserva.getId());
    }
}