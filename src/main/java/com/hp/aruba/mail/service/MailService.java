package com.hp.aruba.mail.service;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.hp.aruba.mail.bean.MailInfo;
import com.hp.aruba.mail.exception.NotifierException;




@SuppressWarnings("deprecation")
@Service
public class MailService implements Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -5586495590115573649L;

	@Autowired
	VelocityEngine velocityEngine;

	
	// Invoking mail.smtp properties
	private @Value("${mail.smtp.protocol}") String mailProtocol;
	private @Value("${mail.smtp.host}") String mailHost;
	private @Value("${mail.smtp.port}") String mailPort;
	private @Value("${mail.smtp.from}") String mailFrom;
	private @Value("${mail.smtp.username}") String mailUsername;
	private @Value("${mail.smtp.password}") String mailPassword;
	private @Value("${mail.path.logo.image}") String mailPathLogoImage;
	private @Value("${mail.security.key}") String mailSecurityKey;
	private @Value("${mail.security.name}") String mailSecurityName;

	@Bean(name = "propertyConfigurer")
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();

		return configurer;
	}

	private JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost(mailHost);
		javaMailSender.setPort(new Integer(mailPort).intValue());
		javaMailSender.setUsername(mailUsername);
		javaMailSender.setPassword(mailPassword);

		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", mailProtocol);
		// properties.setProperty("mail.smtp.auth", "false");
		// properties.setProperty("mail.smtp.starttls.enable", "false");
		// properties.setProperty("mail.debug", "false");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.debug", "true");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.socketFactory.port", mailPort);
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");

		javaMailSender.setJavaMailProperties(properties);

		return javaMailSender;
	}

	public void sendMailTemplate(MailInfo mailInfo, String template) throws NotifierException {
		try {
			
			if(mailInfo.getSecurityId()==null){
				throw new NotifierException("Security ID no definido" );
			}
			else{
				
				if(mailInfo.getSecurityId().getKey().equals(mailSecurityKey) && mailInfo.getSecurityId().getName().equals(mailSecurityName)){
				
					MimeMessagePreparator preparator = new MimeMessagePreparator() {
						public void prepare(javax.mail.internet.MimeMessage mimeMessage) throws Exception {
							System.out.println("==>> inicio mail Template");
							MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
							message.setTo(mailInfo.getMailReceiver().getEmail());
							if (mailInfo.getReceivers() != null && !mailInfo.getReceivers().isEmpty()) {
								String cc[] = new String[mailInfo.getReceivers().size()];
								for (int i = 0; i < mailInfo.getReceivers().size(); i++) {
									cc[i] = mailInfo.getReceivers().get(i).getEmail();
								}
								message.setCc(cc);
							}
							message.setFrom(mailInfo.getMailSender().getEmail());
							message.setSubject(mailInfo.getSubject());
							
							if (mailInfo.getAttachmentList() != null && !mailInfo.getAttachmentList().isEmpty()) {
								for (String attachement : mailInfo.getAttachmentList()) {
									if (attachement != null && !attachement.equals("")) {
										FileSystemResource resAttachement = new FileSystemResource(attachement);
										message.addAttachment(resAttachement.getFilename(), resAttachement);
									}
								}
							}
							Map<String, Object> model = new HashMap<String, Object>();
							model.put("mailInfo", mailInfo);
							model.put("cid", "logo");
							String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, "UTF-8", model);
							message.setText(text, true);
						}
					};
					JavaMailSender javaMailSender = getJavaMailSender();
					javaMailSender.send(preparator);
					System.out.println("==>> fin mail Template");
				}else{
					throw new NotifierException("Credenciales invalidas" );
				}
			
			}
		} catch (Exception e) {
			System.out.println("==>> Exception "+e.getMessage());
			
		}
	}

	public void sendMail(MailInfo mailInfo) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(javax.mail.internet.MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(mailInfo.getMailReceiver().getEmail());
				message.setFrom(mailInfo.getMailSender().getEmail());
				String text = mailInfo.getBody();
				message.setText(text, true);
				message.setSubject(mailInfo.getSubject());
			}
		};
		JavaMailSender javaMailSender = getJavaMailSender();

		if (javaMailSender == null) {
			System.out.println("Mail Sender es nulo");
		}

		javaMailSender.send(preparator);

	}

}
