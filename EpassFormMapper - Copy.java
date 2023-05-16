package com.chainsys.epassproject.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.epassproject.model.EpassForm;

public class EpassFormMapper implements RowMapper<EpassForm> {

	@Override
	public EpassForm mapRow(ResultSet rs, int rowNum) throws SQLException {
		EpassForm epass = new EpassForm();

		Long applicationNo = rs.getLong("application_no");
		epass.setApplicationNo(applicationNo);

		String applicantName = rs.getString("applicant_name");
		epass.setApplicantName(applicantName);

		Long aadharNo = rs.getLong("aadhar_no");
		epass.setAadharNo(aadharNo);

		String gender = rs.getString("gender");
		epass.setGender(gender);

		String reason = rs.getString("reason");
		epass.setReason(reason);

		String fatherName = rs.getString("father_name");
		epass.setFatherName(fatherName);

		Date dob = rs.getDate("dob");
		epass.setDob(dob);

		String fromDistrict = rs.getString("from_district");
		epass.setFromDistrict(fromDistrict);

		String toDistrict = rs.getString("to_district");
		epass.setToDistrict(toDistrict);

		String state = rs.getString("State");
		epass.setState(state);

		Date travelDate = rs.getDate("travel_date");
		epass.setTravelDate(travelDate);

		Date returnDate = rs.getDate("return_date");
		epass.setReturnDate(returnDate);

		Integer noOfPassengers = rs.getInt("no_of_passengers");
		epass.setNoOfPassengers(noOfPassengers);

		String vehicleNo = rs.getString("vehicle_no");
		epass.setVehicleNo(vehicleNo);

		Long mobileNo = rs.getLong("mobile_no");
		epass.setMobileNo(mobileNo);

		String vaccinationCertificate = rs.getString("vaccination_certificate");
		epass.setVaccinationCertificate(vaccinationCertificate);

		String status = rs.getString("status");
		epass.setStatus(status);

		byte[] image = rs.getBytes("Certificate_image");
		String base64Image = Base64.getEncoder().encodeToString(image);
		epass.setImagePath(base64Image);
		epass.setImage(image);
		return epass;
	}
}
