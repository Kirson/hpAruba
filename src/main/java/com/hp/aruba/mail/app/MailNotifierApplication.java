package com.hp.aruba.mail.app;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SuppressWarnings("deprecation")
@Configuration
@PropertySource("classpath:notifier.properties")
@SpringBootApplication
public class MailNotifierApplication implements Serializable{

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 3417719173561064591L;
	//Invoking mail.smtp properties
	private @Value("${mail.smtp.protocol}") String mailProtocol;
	private @Value("${mail.smtp.host}") String mailHost;
	private @Value("${mail.smtp.port}") String mailPort;
	private @Value("${mail.smtp.from}") String mailFrom;
	private @Value("${mail.smtp.username}") String mailUsername;
	private @Value("${mail.smtp.password}") String mailPassword;

	@Bean( name = "propertyConfigurer" )
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = 
                new PropertySourcesPlaceholderConfigurer();

        return configurer;
    }
	
	@Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        System.out.println("==>>"+mailHost);
        javaMailSender.setHost(mailHost);
        javaMailSender.setPort(new Integer(mailPort).intValue());
        javaMailSender.setUsername(mailUsername);
        javaMailSender.setPassword(mailPassword);
        
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", mailProtocol);
        properties.setProperty("mail.smtp.auth", "false");
        properties.setProperty("mail.smtp.starttls.enable", "false");
        properties.setProperty("mail.debug", "false");

        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }

	
    
	@Bean
    public VelocityEngine velocityEngine() throws VelocityException, IOException{
    	VelocityEngineFactoryBean egn = new VelocityEngineFactoryBean();
    	
    	VelocityEngine engine = egn.createVelocityEngine();
    	
    	Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    	
    	return engine;
    }
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    MappingJackson2HttpMessageConverter converter = 
	        new MappingJackson2HttpMessageConverter(mapper);
	    return converter;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(MailNotifierApplication.class, args);
	}
}
