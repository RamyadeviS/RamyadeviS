package com.chainsys.epassproject.model;

public class UserDetails {

	private Long aadharNo;
	private String userName;
	private String mailId;
	private String password;
	private Long mobileNo;

	public UserDetails() {

	}

	public UserDetails(Long aadharNo, String userName, String mailId, String password, Long mobileNo) {
		super();
		this.aadharNo = aadharNo;
		this.userName = userName;
		this.mailId = mailId;
		this.password = password;
		this.mobileNo = mobileNo;
	}

	public Long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(Long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "Register [aadharNo=" + aadharNo + ", userName=" + userName + ", mailId=" + mailId + ", password="
				+ password + ", mobileNo=" + mobileNo + "]";
	}

}