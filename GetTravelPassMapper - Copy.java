package com.chainsys.epassproject.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.epassproject.model.TravelEpassForm;

public class GetTravelPassMapper implements RowMapper<TravelEpassForm> {

	@Override
	public TravelEpassForm mapRow(ResultSet rs, int rowNum) throws SQLException {
		TravelEpassForm travelEpass = new TravelEpassForm();

		Long applicationNo = rs.getLong("application_no");
		travelEpass.setApplicationNumber(applicationNo);

		String applicantName = rs.getString("applicant_name");
		travelEpass.setApplicantName1(applicantName);

		String ticketNo = rs.getString("ticket_no");
		travelEpass.setTicketNo(ticketNo);

		Long aadharNo = rs.getLong("aadhar_no");
		travelEpass.setAadharNumber(aadharNo);

		Long mobileNo = rs.getLong("mobile_no");
		travelEpass.setPhoneNumber(mobileNo);

		String source = rs.getString("source");
		travelEpass.setSource(source);

		String destination = rs.getString("destination");
		travelEpass.setDestination(destination);

		Date travelDate = rs.getDate("travel_date");
		travelEpass.setFromDate(travelDate);

		Date returnDate = rs.getDate("return_date");
		travelEpass.setToDate(returnDate);

		String reason = rs.getString("reason");
		travelEpass.setReasons(reason);

		Integer noOfPassengers = rs.getInt("no_of_passengers");
		travelEpass.setPassengers(noOfPassengers);
		
		String action = rs.getString("status");
		travelEpass.setAction(action);

		return travelEpass;

	}
}

