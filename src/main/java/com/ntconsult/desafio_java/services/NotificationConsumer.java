package com.ntconsult.desafio_java.services;

import com.ntconsult.desafio_java.configs.RabbitMQConfig;
import com.ntconsult.desafio_java.dtos.NotificationDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveNotification(NotificationDTO notification) {

        System.out.println("Notificação recebida: " + notification);
    }
}
