package com.hp.aruba.mail.bean;

import java.io.Serializable;

public class MailResponse implements Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 7512796018786132018L;
	
	private String result;
	private String message;
    private String exceptionMessage;
    private MailInfo mailInfo;
	
    public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public MailInfo getMailInfo() {
		return mailInfo;
	}
	public void setMailInfo(MailInfo mailInfo) {
		this.mailInfo = mailInfo;
	}
    
    
}
