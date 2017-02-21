package com.hp.aruba.mail.exception;

public class NotifierException extends Exception {

	
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -1807923571990490466L;
	
	private String message;

	
	public NotifierException(String message) {
		this.message=message;
		
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
