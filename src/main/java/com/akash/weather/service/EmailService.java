/**
 * 
 */
package com.akash.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Akash Bais
 *
 */
	
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

	public void sendEmailAlert(String email, Double currentTemperature) throws Exception {
    	String finalEmail = email.trim();
    	if(finalEmail.isBlank()) throw new Exception("email invalid");
        String subject = "Temperature Alert!";
        String message = "The current temperature is " + currentTemperature + "°C. " +
                         "Please take the necessary precautions.";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("akashbais41203@gmail.com");

        mailSender.send(mailMessage);
    }
}
