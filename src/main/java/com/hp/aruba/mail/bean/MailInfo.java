package com.hp.aruba.mail.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MailInfo implements Serializable {

	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 7094710821566935074L;
	private MailParticipant mailSender;
	private MailParticipant mailReceiver;
	private List<MailParticipant> receivers;
	private String subject;
	private String body;
	private String htmlBody;
	private List<String> attachmentList;
	private String generalText;
	private List<String> textList;
	private Boolean sendMail;
	private MailSecurityId securityId;
	
	public MailInfo(){
		attachmentList = new ArrayList<String>();
		textList = new ArrayList<String>();
		sendMail = Boolean.FALSE;
	}
	
	public MailParticipant getMailSender() {
		return mailSender;
	}
	public void setMailSender(MailParticipant mailSender) {
		this.mailSender = mailSender;
	}
	public MailParticipant getMailReceiver() {
		return mailReceiver;
	}
	public void setMailReceiver(MailParticipant mailReceiver) {
		this.mailReceiver = mailReceiver;
	}
	public List<MailParticipant> getReceivers() {
		return receivers;
	}
	public void setReceivers(List<MailParticipant> receivers) {
		this.receivers = receivers;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getHtmlBody() {
		return htmlBody;
	}
	public void setHtmlBody(String htmlBody) {
		this.htmlBody = htmlBody;
	}
	
	public List<String> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<String> attachmentList) {
		this.attachmentList = attachmentList;
	}

	
	public String getGeneralText() {
		return generalText;
	}

	public void setGeneralText(String generalText) {
		this.generalText = generalText;
	}

	

	public List<String> getTextList() {
		return textList;
	}

	public void setTextList(List<String> textList) {
		this.textList = textList;
	}

	

	public Boolean getSendMail() {
		return sendMail;
	}

	public void setSendMail(Boolean sendMail) {
		this.sendMail = sendMail;
	}

	

	public MailSecurityId getSecurityId() {
		return securityId;
	}

	public void setSecurityId(MailSecurityId securityId) {
		this.securityId = securityId;
	}
	
	
	
}
