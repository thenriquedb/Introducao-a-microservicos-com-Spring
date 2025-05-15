package com.thenriquedb.ms_email.services;

import com.thenriquedb.ms_email.domain.Email;
import com.thenriquedb.ms_email.enums.StatusEmail;
import com.thenriquedb.ms_email.repositories.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
    final EmailRepository emailRepository;
    final JavaMailSender mailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public void sendEmail(Email email) {
        try {
            email.setSendDateEmail(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getContent());

            mailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);
            System.out.println("Email sent successfully to: " + email.getEmailTo());
        } catch (Exception e) {
            email.setStatusEmail(StatusEmail.ERROR);
            System.out.println("Error sending email to: " + email.getEmailTo() + " - " + e.getMessage());
        } finally {
            emailRepository.save(email);
        }
    }
}
