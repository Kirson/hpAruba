package com.hp.aruba.mail.bean;

import java.io.Serializable;

public class MailParticipant implements Serializable {

	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 2394157103048652758L;
	private String name;
	private String type;
	private String email;
	
	public MailParticipant() {
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}
