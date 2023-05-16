package com.chainsys.epassproject.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;

import com.chainsys.epassproject.mapper.AdminLoginMapper;
import com.chainsys.epassproject.mapper.EpassFormMapper;
import com.chainsys.epassproject.mapper.TravelEpassFormMapper;
import com.chainsys.epassproject.model.AdminLogin;
import com.chainsys.epassproject.model.EpassForm;
import com.chainsys.epassproject.model.TravelEpassForm;
import com.chainsys.epassproject.model.UserDetails;
import com.chainsys.epassproject.util.ConnectionUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminDao implements AdminInterface {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger = LoggerFactory.getLogger(AdminDao.class);
	// crud-DB activities

	@Override
	public boolean adminLogin(UserDetails register, Model model) {
		String mailId = register.getMailId();
		String adminPass = register.getPassword();
		String mail = "selvarasu@gmail.com";
		String pass = "Selva@9787";
		String status;

		String query = "select mail_id, password from user_details where mail_id=?";
	UserDetails adminList = jdbcTemplate.queryForObject(query, new AdminLoginMapper(), mailId);

			if (adminList != null) {
				if (adminPass.equals(pass)) {
					if (mailId.equals(mail)) {
						logger.info("Login Successully");
						status = "Successfully";
						model.addAttribute("LoginSuccessfully", status);
						return true;
					}
				} else {
					logger.info("Invalid Password");
					status = "Invalid Credentials";
					model.addAttribute("InvalidLogin", status);
					return false;
				}
			}
			status = "Invalid Credentials";
			model.addAttribute("InvalidLogin", status);
		
		return false;
	}
	
	public void listEpassform(Model model) throws JsonProcessingException {
		String sql = "Select application_no, applicant_name,aadhar_no ,gender,reason,father_name ,dob, from_district ,to_district ,state ,travel_date ,return_date,no_of_passengers ,vehicle_no, mobile_no ,vaccination_certificate,status,Certificate_image from epass_apply order by application_no DESC";
		List<EpassForm> epassView = jdbcTemplate.query(sql, new EpassFormMapper());
		List<Map<String, Object>> epassList = new ArrayList<>();
		for (EpassForm list : epassView) {
			Map<String, Object> userListMap = new HashMap<>();
			userListMap.put("application_no", list.getApplicationNo());
			userListMap.put("applicant_name", list.getApplicantName());
			userListMap.put("aadhar_no", list.getAadharNo());
			userListMap.put("gender", list.getGender());
			userListMap.put("reason", list.getReason());
			userListMap.put("father_name", list.getFatherName());
			userListMap.put("dob", list.getDob());
			userListMap.put("from_district", list.getFromDistrict());
			userListMap.put("to_district", list.getToDistrict());
			userListMap.put("state", list.getState());
			userListMap.put("travel_date", list.getTravelDate());
			userListMap.put("return_date", list.getReturnDate());
			userListMap.put("no_of_passengers", list.getNoOfPassengers());
			userListMap.put("vehicle_no", list.getVehicleNo());
			userListMap.put("mobile_no", list.getMobileNo());
			userListMap.put("status", list.getStatus());
			userListMap.put("vaccination_certificate", list.getVaccinationCertificate());
			userListMap.put("Certificate_image", list.getImage());

			epassList.add(userListMap);

		}
		ObjectMapper epassMapper = new ObjectMapper();
		String epassAction = epassMapper.writeValueAsString(epassList);
		model.addAttribute("userList", epassAction);
	}

	public List<EpassForm> listEpassformImage(Integer appId) {
		String sql = "Select application_no, applicant_name,aadhar_no ,gender,reason,father_name ,dob, from_district ,to_district ,state ,travel_date ,return_date,no_of_passengers ,vehicle_no, mobile_no ,vaccination_certificate,status,Certificate_image from epass_apply where application_no=? order by application_no DESC";
		return jdbcTemplate.query(sql, new EpassFormMapper(), appId);
	}

	public List<EpassForm> approveEpassformImage(Integer appNo) {
		String sql = "Select application_no, applicant_name,aadhar_no ,gender,reason,father_name ,dob, from_district ,to_district ,state ,travel_date ,return_date,no_of_passengers ,vehicle_no, mobile_no ,vaccination_certificate,status,Certificate_image from epass_apply where status='Approved' order by application_no DESC";
		return jdbcTemplate.query(sql, new EpassFormMapper(), appNo);
	}

	public void listTravelPassForm(Model model) throws JsonProcessingException {
		String sql = "Select application_no,applicant_name,gender,father_name,ticket_no,dob,aadhar_no,mobile_no,source,destination,travel_date,return_date,reason,no_of_passengers,vaccination_certificate,status,Certificate_image from travel_history order by application_no DESC";
		List<TravelEpassForm> travelPassView = jdbcTemplate.query(sql, new TravelEpassFormMapper());
		List<Map<String, Object>> travelList = new ArrayList<>();
		for (TravelEpassForm travel : travelPassView) {
			Map<String, Object> travelListMap = new HashMap<>();
			travelListMap.put("application_no", travel.getApplicationNumber());
			travelListMap.put("applicant_name1", travel.getApplicantName1());
			travelListMap.put("gender", travel.getGender1());
			travelListMap.put("father_name1", travel.getFatherName1());
			travelListMap.put("ticket_no", travel.getTicketNo());
			travelListMap.put("dateOfBirth", travel.getDateOfBirth());
			travelListMap.put("aadhar_number", travel.getAadharNumber());
			travelListMap.put("mobile_number", travel.getPhoneNumber());
			travelListMap.put("source", travel.getSource());
			travelListMap.put("destination", travel.getDestination());
			travelListMap.put("from_date", travel.getFromDate());
			travelListMap.put("to_date", travel.getToDate());
			travelListMap.put("reasons", travel.getReasons());
			travelListMap.put("number_of_passengers", travel.getPassengers());
			travelListMap.put("vaccine_certificate", travel.getCovidCertificate());
			travelListMap.put("action", travel.getAction());
			travelListMap.put("vaccine_Certificate_image", travel.getImages());

			travelList.add(travelListMap);

		}
		ObjectMapper travelMapper = new ObjectMapper();
		String travelAction = travelMapper.writeValueAsString(travelList);
		model.addAttribute("viewList", travelAction);

	}

	public List<TravelEpassForm> listTravelPassFormImage(Integer appId1) {
		String sql = "Select application_no,applicant_name,gender,father_name,ticket_no,dob,aadhar_no,mobile_no,source,destination,travel_date,return_date,reason,no_of_passengers,vaccination_certificate,status,Certificate_image from travel_history where application_no=? order by application_no DESC";
		return jdbcTemplate.query(sql, new TravelEpassFormMapper(), appId1);
	}

	public void epassStatusUpdate(EpassForm epass) {
		logger.info("To Approve Epass");
		Long appNo = epass.getApplicationNo();
		appNo.toString();
		String updateStatus = "Update epass_apply set status='Approved'where application_no=?";
		Object[] params = { appNo };
		jdbcTemplate.update(updateStatus, params);
	}

	public void epassStatusReject(EpassForm epass) {
		logger.info("To Reject Epass");
		String updateStatus = "update epass_apply set status='Rejected' where application_no=?";
		Object[] params = { epass.getApplicationNo() };
		jdbcTemplate.update(updateStatus, params);
	}

	public void travelStatusApprove(TravelEpassForm travelPass) {
		logger.info("To Approve Travel Pass");
		String status = "update travel_history set status='Approved' where application_no=?";
		Object[] params = { travelPass.getApplicationNumber() };
		jdbcTemplate.update(status, params);
	}

	public void travelStatusReject(TravelEpassForm travelPass) {
		logger.info("To Reject Travel Pass");
		String status = "update travel_history set status='Rejected' where application_no=?";
		Object[] params = { travelPass.getApplicationNumber() };
		jdbcTemplate.update(status, params);
	}

	public Integer totalEpassCount() {
		String sql = "SELECT COUNT(*) FROM epass_apply";
		return jdbcTemplate.queryForObject(sql, Integer.class);

	}

	public Integer totalTravelPassCount() {
		String sql = "SELECT COUNT(*) FROM travel_history";
		return jdbcTemplate.queryForObject(sql, Integer.class);

	}

	public Integer totalUserCount() {
		String sql = "Select (select count(*) from epass_apply) + (select count(*) from travel_history) sum from dual";
		return jdbcTemplate.queryForObject(sql, Integer.class);

	}

	public Integer approvedCount() {
		String sql = "Select count(*) status from  epass_apply where status = 'Approved'";
		return jdbcTemplate.queryForObject(sql, Integer.class);

	}

	public Integer rejectedCount() {
		String sql1 = "Select count(*) status from  epass_apply where status = 'Rejected'";
		return jdbcTemplate.queryForObject(sql1, Integer.class);

	}

	public Integer approveCount() {
		String sql = "Select count(*) status from  travel_history where status = 'Approved'";
		return jdbcTemplate.queryForObject(sql, Integer.class);

	}

	public Integer rejectCount() {
		String sql1 = "Select count(*) status from  travel_history where status = 'Rejected'";
		return jdbcTemplate.queryForObject(sql1, Integer.class);

	}

	public Integer pendingEpassCount() {
		String sql = "Select count(*) status from  epass_apply where status = 'Pending'";

		return jdbcTemplate.queryForObject(sql, Integer.class);

	}

	public Integer pendingCount() {
		String sql = "Select count(*) status from  travel_history where status = 'Pending'";
		return jdbcTemplate.queryForObject(sql, Integer.class);

	}
}