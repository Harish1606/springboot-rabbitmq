package com.ford.springbootrabbitmqpublisher.publisher;

import com.ford.springbootrabbitmqpublisher.config.MessagingConfig;
import com.ford.springbootrabbitmqpublisher.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        order.setStatus("PROCESS");
        order.setMessage("Order placed successfully in "+restaurantName);
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, order);
        return "Success !!!";
    }
}
