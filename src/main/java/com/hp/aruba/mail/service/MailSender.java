package com.hp.aruba.mail.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

import com.hp.aruba.mail.bean.MailInfo;
import com.hp.aruba.mail.bean.MailParticipant;
import com.hp.aruba.mail.bean.MailResponse;
import com.hp.aruba.mail.exception.NotifierException;

@Service
public class MailSender implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4738595483517181879L;

	@Autowired
	MailService mailService;
	
	private @Value("${mail.smtp.from}") String mailFrom;
	private @Value("${mail.smtp.username}") String mailUsername;
	
	@Bean(name = "propertyConfigurer")
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();

		return configurer;
	}
	
	public MailResponse sendMail(MailInfo mailInfo){
		
		MailResponse response = new MailResponse();
		
		response.setResult("EXITO");
		
		try{
			
			if(mailInfo.getMailSender()==null){
				MailParticipant mp = new MailParticipant();
				mp.setEmail(mailFrom);
				mp.setName(mailUsername);
				mailInfo.setMailSender(mp);
			}
			
			mailService.sendMailTemplate(mailInfo, "mailNotification.vm");
			response.setMessage("Correo enviado en forma exitosa");
		}catch(NotifierException e){
			response.setResult("ERROR");
			response.setExceptionMessage(e.getMessage());
			response.setMessage("Correo no enviado");
		}
		catch(Exception e){
			response.setResult("ERROR");
			response.setExceptionMessage(e.getMessage());
			response.setMessage("Correo no enviado");
		}
		
		return response;
	}
}
