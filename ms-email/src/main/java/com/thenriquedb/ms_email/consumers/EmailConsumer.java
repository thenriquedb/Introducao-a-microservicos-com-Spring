package com.thenriquedb.ms_email.consumers;

import com.thenriquedb.ms_email.domain.Email;
import com.thenriquedb.ms_email.dtos.EmailRecordDto;
import com.thenriquedb.ms_email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailRecordDto, email);

        System.out.println("Received email: " + email);

        emailService.sendEmail(email);
    }
}
