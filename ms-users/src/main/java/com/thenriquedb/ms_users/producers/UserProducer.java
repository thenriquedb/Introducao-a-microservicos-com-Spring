package com.thenriquedb.ms_users.producers;

import com.thenriquedb.ms_users.domain.User;
import com.thenriquedb.ms_users.dtos.EmailRecordDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {
    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user) {
        var emailDto = new EmailRecordDto(
                user.getId(),
                user.getEmail(),
                "Welcome to our service",
                "Hello " + user.getName() + ", welcome to our service. We are glad to have you here!"
        );

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

}
