package com.ntconsult.desafio_java.services;

import com.ntconsult.desafio_java.configs.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveNotification(String notification) {

        System.out.println("Notificação recebida: " + notification);
    }
}
