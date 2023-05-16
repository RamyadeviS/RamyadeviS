package com.chainsys.epassproject.model;

import java.sql.Date;
import java.util.Arrays;

public class EpassForm {

	private Long applicationNo;
	private String applicantName;
	private Long aadharNo;
	private String gender;
	private String reason;
	private String fatherName;
	private Date dob;
	private String fromDistrict;
	private String toDistrict;
	private String state;
	private Date travelDate;
	private Date returnDate;
	private Integer noOfPassengers;
	private String vehicleNo;
	private Long mobileNo;
	private String vaccinationCertificate;
	private String status;
	private byte[] image;
	private String imagePath;

	public EpassForm() {
		super();
	}

	public EpassForm(Long applicationNo, String applicantName, Long aadharNo, String gender, String reason,
			String fatherName, Date dob) {
		super();
		this.applicationNo = applicationNo;
		this.applicantName = applicantName;
		this.aadharNo = aadharNo;
		this.gender = gender;
		this.reason = reason;
		this.fatherName = fatherName;
		this.dob = dob;
	}

	public EpassForm(String fromDistrict, String toDistrict, String state, Date travelDate, Date returnDate,
			Integer noOfPassengers) {
		super();
		this.fromDistrict = fromDistrict;
		this.toDistrict = toDistrict;
		this.state = state;
		this.travelDate = travelDate;
		this.returnDate = returnDate;
		this.noOfPassengers = noOfPassengers;
	}

	public EpassForm(String vehicleNo, Long mobileNo, String vaccinationCertificate, String status, byte[] image,
			String imagePath) {
		super();
		this.vehicleNo = vehicleNo;
		this.mobileNo = mobileNo;
		this.vaccinationCertificate = vaccinationCertificate;
		this.status = status;
		this.image = image;
		this.imagePath = imagePath;
	}

	public Long getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(Long applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(Long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFromDistrict() {
		return fromDistrict;
	}

	public void setFromDistrict(String fromDistrict) {
		this.fromDistrict = fromDistrict;
	}

	public String getToDistrict() {
		return toDistrict;
	}

	public void setToDistrict(String toDistrict) {
		this.toDistrict = toDistrict;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Integer getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(Integer noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getVaccinationCertificate() {
		return vaccinationCertificate;
	}

	public void setVaccinationCertificate(String vaccinationCertificate) {
		this.vaccinationCertificate = vaccinationCertificate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "EpassForm [applicationNo=" + applicationNo + ", applicantName=" + applicantName + ", aadharNo="
				+ aadharNo + ", gender=" + gender + ", reason=" + reason + ", fatherName=" + fatherName + ", dob=" + dob
				+ ", fromDistrict=" + fromDistrict + ", toDistrict=" + toDistrict + ", state=" + state + ", travelDate="
				+ travelDate + ", returnDate=" + returnDate + ", noOfPassengers=" + noOfPassengers + ", vehicleNo="
				+ vehicleNo + ", mobileNo=" + mobileNo + ", vaccinationCertificate=" + vaccinationCertificate
				+ ", status=" + status + ", image=" + Arrays.toString(image) + ", imagePath=" + imagePath + "]";
	}

}

