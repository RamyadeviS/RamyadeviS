package com.chainsys.epassproject.model;

public class AdminLogin {

	private String mailId;
	private String password;

	public AdminLogin() {

	}

	public AdminLogin(String mailId, String password) {
		super();
		this.mailId = mailId;
		this.password = password;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AdminLogin [mailId=" + mailId + ", password=" + password + "]";
	}

}


