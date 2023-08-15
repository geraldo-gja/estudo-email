package br.com.feltex.enviaremail.service;

import java.util.HashMap;

import javax.mail.MessagingException;

/**
 * This service class handles sending emails using JavaMailSender.
 *
 * @author Geraldo Jorge
 * email: geraldo.gja@gmail.com
 * @version 1.0
 * Data: 01/06/2023
 */
public interface SendEmailService {

	/**
	 * Sends a simple email.
	 *
	 * @param to      The recipient's email address.
	 * @param subject The subject of the email.
	 * @param content The content of the email.
	 */
	public void sendSimpleEmail(String to, String subject, String content);

	/**
	 * Sends an email with attachments.
	 *
	 * @param to      The recipient's email address.
	 * @param subject The subject of the email.
	 * @param content The content of the email.
	 * @param files   A map containing file names and their corresponding classpath
	 *                resources.
	 * @throws MessagingException if there's an issue with sending the email.
	 */
	public void sendEmailWithAttachments(String to, String subject, String content, HashMap<String, String> files)
			throws MessagingException;
}
