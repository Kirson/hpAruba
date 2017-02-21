package com.hp.aruba.mail.controller;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hp.aruba.mail.bean.MailInfo;
import com.hp.aruba.mail.bean.MailResponse;
import com.hp.aruba.mail.service.MailSender;

@RestController
@RequestMapping(value = "/mailController")
public class MailController implements Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -1852544776026577995L;
	
	@Autowired
	MailSender mailSender;
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public ResponseEntity<MailResponse> deleteSupplier(@RequestBody MailInfo mailInfo){
		
		MailResponse response = mailSender.sendMail(mailInfo);
		
		return new ResponseEntity<MailResponse>(response,HttpStatus.OK);
	}

}
