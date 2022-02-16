package com.ford.springbootrabbitmqconsumer.consumer;

import com.ford.springbootrabbitmqconsumer.config.MessagingConfig;
import com.ford.springbootrabbitmqconsumer.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Order order) {
        System.out.println("Message received from queue : " + order);
    }
}
