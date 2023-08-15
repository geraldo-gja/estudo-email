package br.com.feltex.enviaremail.service;

import java.util.HashMap;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * This service class handles sending emails using JavaMailSender.
 *
 * @author Geraldo Jorge
 * email: geraldo.gja@gmail.com
 * @version 1.0
 * Data: 01/06/2023
 */
@Service
@Slf4j
public class SendEmailServiceImpl implements SendEmailService {

	@Autowired
    private JavaMailSender javaMailSender;

	/**
     * {@inheritDoc}
     */
	@Override
    public void sendSimpleEmail(String to, String subject, String content) {
        log.info("Sending simple email");

        var message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        
        javaMailSender.send(message);       
        log.info("Simple email sent successfully!");
    }

    /**
     * {@inheritDoc}
     */
	@Override
    public void sendEmailWithAttachments(String to, String subject, String content, HashMap<String, String> files)
            throws MessagingException {

        log.info("Sending email with attachments");
        var message = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        files.forEach((key, value) -> {
            try {
                helper.addAttachment(key, new ClassPathResource(value));
            } catch (MessagingException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        });

        javaMailSender.send(message);
        log.info("Email with attachments sent successfully.");
    }
}
