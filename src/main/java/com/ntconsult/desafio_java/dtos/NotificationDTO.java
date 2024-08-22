package com.ntconsult.desafio_java.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class NotificationDTO implements Serializable {

    private Long reservaId;

    private String tipoNotificacao;

    private String statusNotificacao;

    private String dataEnvio;

    public NotificationDTO(Long reservaId, String tipoNotificacao, String statusNotificacao, String dataEnvio) {
        this.reservaId = reservaId;
        this.tipoNotificacao = tipoNotificacao;
        this.statusNotificacao = statusNotificacao;
        this.dataEnvio = dataEnvio;
    }
}
