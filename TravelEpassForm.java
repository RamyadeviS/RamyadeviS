package com.chainsys.epassproject.model;

import java.sql.Date;
import java.util.Arrays;
public class TravelEpassForm {

	private Long applicationNumber;
	private String applicantName1;
	private String gender1;
	private String fatherName1;
	private String ticketNo;
	private Date dateOfBirth;
	private Long aadharNumber;
	private Long phoneNumber;
	private String source;
	private String destination;
	private Date fromDate;
	private Date toDate;
	private String reasons;
	private Integer passengers;
	private String covidCertificate;
	private String action;
	private byte[] images;
	private String imagePath1;

	public TravelEpassForm() {

	}

	public TravelEpassForm(Long applicationNumber, String applicantName1, String gender1, String fatherName1,
			String ticketNo, Date dateOfBirth) {
		super();
		this.applicationNumber = applicationNumber;
		this.applicantName1 = applicantName1;
		this.gender1 = gender1;
		this.fatherName1 = fatherName1;
		this.ticketNo = ticketNo;
		this.dateOfBirth = dateOfBirth;
	}

	public TravelEpassForm(Long aadharNumber, Long phoneNumber, String source, String destination, Date fromDate,
			Date toDate) {
		super();
		this.aadharNumber = aadharNumber;
		this.phoneNumber = phoneNumber;
		this.source = source;
		this.destination = destination;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public TravelEpassForm(String reasons, Integer passengers, String covidCertificate, String action, byte[] images,
			String imagePath1) {
		super();
		this.reasons = reasons;
		this.passengers = passengers;
		this.covidCertificate = covidCertificate;
		this.action = action;
		this.images = images;
		this.imagePath1 = imagePath1;
	}

	public Long getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(Long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getApplicantName1() {
		return applicantName1;
	}

	public void setApplicantName1(String applicantName1) {
		this.applicantName1 = applicantName1;
	}

	public String getGender1() {
		return gender1;
	}

	public void setGender1(String gender1) {
		this.gender1 = gender1;
	}

	public String getFatherName1() {
		return fatherName1;
	}

	public void setFatherName1(String fatherName1) {
		this.fatherName1 = fatherName1;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(Long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public Integer getPassengers() {
		return passengers;
	}

	public void setPassengers(Integer passengers) {
		this.passengers = passengers;
	}

	public String getCovidCertificate() {
		return covidCertificate;
	}

	public void setCovidCertificate(String covidCertificate) {
		this.covidCertificate = covidCertificate;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public byte[] getImages() {
		return images;
	}

	public void setImages(byte[] images) {
		this.images = images;
	}

	public String getImagePath1() {
		return imagePath1;
	}

	public void setImagePath1(String imagePath1) {
		this.imagePath1 = imagePath1;
	}

	@Override
	public String toString() {
		return "TravelEpassForm [applicationNumber=" + applicationNumber + ", applicantName1=" + applicantName1
				+ ", gender1=" + gender1 + ", fatherName1=" + fatherName1 + ", ticketNo=" + ticketNo + ", dateOfBirth="
				+ dateOfBirth + ", aadharNumber=" + aadharNumber + ", phoneNumber=" + phoneNumber + ", source=" + source
				+ ", destination=" + destination + ", fromDate=" + fromDate + ", toDate=" + toDate + ", reasons="
				+ reasons + ", passengers=" + passengers + ", covidCertificate=" + covidCertificate + ", action="
				+ action + ", images=" + Arrays.toString(images) + ", imagePath1=" + imagePath1 + "]";
	}

	
}
