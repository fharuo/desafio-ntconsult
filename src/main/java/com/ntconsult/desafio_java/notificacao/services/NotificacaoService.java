package com.ntconsult.desafio_java.notificacao.services;

import com.ntconsult.desafio_java.dtos.NotificationDTO;
import com.ntconsult.desafio_java.exceptions.NotFoundException;
import com.ntconsult.desafio_java.notificacao.models.Notificacao;
import com.ntconsult.desafio_java.notificacao.models.StatusNotificacao;
import com.ntconsult.desafio_java.notificacao.models.TipoNotificacao;
import com.ntconsult.desafio_java.notificacao.repositories.NotificacaoRepository;
import com.ntconsult.desafio_java.notificacao.repositories.StatusNotificacaoRepository;
import com.ntconsult.desafio_java.notificacao.repositories.TipoNotificacaoRepository;
import com.ntconsult.desafio_java.reserva.models.Reserva;
import com.ntconsult.desafio_java.reserva.repositories.ReservaRepository;
import com.ntconsult.desafio_java.services.NotificationProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    private final ReservaRepository reservaRepository;

    private final StatusNotificacaoRepository statusNotificacaoRepository;

    private final TipoNotificacaoRepository tipoNotificacaoRepository;

    private final NotificationProducer notificationProducer;

    public void enviarNotificacao(NotificationDTO notificationDTO) {
        TipoNotificacao tipoNotificacao = tipoNotificacaoRepository.findByDescricao(notificationDTO.getTipoNotificacao())
                .orElseThrow(() -> new NotFoundException("Tipo de Notificação não encontrado"));

        StatusNotificacao statusNotificacao = statusNotificacaoRepository.findByDescricao(notificationDTO.getStatusNotificacao())
                .orElseThrow(() -> new NotFoundException("Status de Notificação não encontrado"));

        Reserva reserva = reservaRepository.findById(notificationDTO.getReservaId())
                .orElseThrow(() -> new NotFoundException("Status de Notificação não encontrado"));

        Notificacao notificacao = new Notificacao();
        notificacao.setReserva(reserva);
        notificacao.setTipo(tipoNotificacao);
        notificacao.setStatus(statusNotificacao);

        notificacaoRepository.save(notificacao);

        notificationProducer.sendNotification(notificationDTO);
        System.out.println("Notificação enviada: " + notificationDTO.getTipoNotificacao() + " para a reserva " + notificationDTO.getReservaId());
    }
}