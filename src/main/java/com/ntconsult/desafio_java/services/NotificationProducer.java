package com.ntconsult.desafio_java.services;

import com.ntconsult.desafio_java.configs.RabbitMQConfig;
import com.ntconsult.desafio_java.dtos.NotificationDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationProducer {

    private RabbitTemplate rabbitTemplate;

    public void sendNotification(NotificationDTO notification) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "notification.email", notification);
    }
}