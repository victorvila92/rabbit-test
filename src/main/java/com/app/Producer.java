package com.app;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.time.LocalDateTime;

public class Producer {

    private static final String QUEUE_NAME = "victor-test";

    public static void  main (String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "messaging-victor-queue " + LocalDateTime.now();

            channel.basicPublish("", QUEUE_NAME, false, false, null, message.getBytes());

            System.out.println("Message has been sent");
        } catch (Exception e) {
            System.out.println("ERROR. Message: " + e);
        }
    }
}
