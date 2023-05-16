package com.chainsys.epassproject.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import com.chainsys.epassproject.mapper.AadharNoMapper;
import com.chainsys.epassproject.mapper.EpassFormMapper;
import com.chainsys.epassproject.mapper.EpassStatusMapper;
import com.chainsys.epassproject.mapper.GetEpassMapper;
import com.chainsys.epassproject.mapper.GetTravelPassMapper;
import com.chainsys.epassproject.mapper.MailIdExistingMapper;
import com.chainsys.epassproject.mapper.ProfileMapper;
import com.chainsys.epassproject.mapper.TravelEpassFormMapper;
import com.chainsys.epassproject.mapper.TravelPassStausMapper;
import com.chainsys.epassproject.mapper.UserLoginMapper;
import com.chainsys.epassproject.model.AdminLogin;
import com.chainsys.epassproject.model.EpassForm;
import com.chainsys.epassproject.model.TravelEpassForm;
import com.chainsys.epassproject.model.UserDetails;
import com.chainsys.epassproject.myexception.InvalidPwdException;
import com.chainsys.epassproject.util.ConnectionUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EpassDao implements EpassInterface {
	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();
	Logger logger = LoggerFactory.getLogger(EpassDao.class);
	// crud-DB activities

	public void userRegister(UserDetails register, HttpSession session) {
		logger.info("To register Epass");
		String password = register.getPassword();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(password);

		String user = "Insert into user_details(aadhar_no,user_name,mail_id,password,mobile_no)values(?,?,?,?,?)";
		Object[] params = { register.getAadharNo(), register.getUserName(), register.getMailId(), encodedPassword,
				register.getMobileNo() };
		jdbcTemplate.update(user, params);
		session.setAttribute("UserName", register.getUserName());
		session.setAttribute("AadharNo", register.getAadharNo());
		session.setAttribute("MobileNo", register.getMobileNo());
	}

	public Boolean mailIdExisting(String mailId, Model model) {
		String query = "Select mail_id from user_details";
		List<UserDetails> regList = jdbcTemplate.query(query, new MailIdExistingMapper());
		for (UserDetails registers : regList) {
			String email = registers.getMailId();
			logger.info(email);

			if (email.equals(mailId)) {
				String errorMessage = "Email Id Already exist ";
				model.addAttribute("errorMessage17", errorMessage);

				return false;

			}
		}
		return true;
	}

	public Boolean aadharNoExisting(Long aadharNo, Model model) {
		UserDetails register = new UserDetails();
		register.getAadharNo();

		String query = "Select aadhar_no from user_details";
		List<UserDetails> regList = jdbcTemplate.query(query, new AadharNoMapper());
		for (UserDetails registers : regList) {
			Long aNo = registers.getAadharNo();
			if (aNo.equals(aadharNo)) {
				String errorMessage = "Aadhar Number Already exist ";
				model.addAttribute("errorMessage18", errorMessage);
				return false;

			} else
				logger.info("Valid Aadhar Number ");
		}
		return true;
	}

	public boolean userLogin(UserDetails register, HttpSession session) {

		String userName = register.getUserName();
		String password = register.getPassword();
		Long aadharNo = register.getAadharNo();

		logger.info("To Login user");
		String query = "Select user_name,password,aadhar_no,mobile_no from user_details where user_name=?";
 
		UserDetails userList = jdbcTemplate.queryForObject(query, new UserLoginMapper(), userName);
		session.setAttribute("UserName", register.getUserName());
		session.setAttribute("AadharNo", register.getAadharNo());

		    userList.getUserName();
			String pwd = userList.getPassword();
			Long aNo = userList.getAadharNo();
			Long mNo = userList.getMobileNo();
			session.setAttribute("MobileNo", register.getMobileNo());

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			boolean match = encoder.matches(password, pwd);
			if ((match)) {

				String sql = "Select application_no, applicant_name,aadhar_no ,reason,from_district ,to_district,travel_date ,return_date,no_of_passengers,vehicle_no, mobile_no,status from epass_apply where application_no=?";
				List<EpassForm> getEpass = jdbcTemplate.query(sql, new GetEpassMapper(), aadharNo);
				List<Map<String, Object>> epassGetList = new ArrayList<>();
				for (EpassForm approveList : getEpass) {
					Map<String, Object> approveListMap = new HashMap<>();

					approveListMap.put("Application No", approveList.getApplicationNo());
					Long applicationNo = approveList.getApplicationNo();
					session.setAttribute("ApplicationNumber", applicationNo);

					approveListMap.put("applicant_Name", approveList.getApplicantName());
					String name = approveList.getApplicantName();
					session.setAttribute("Applicant_Name", name);

					approveListMap.put("aadhar_number", approveList.getAadharNo());
					Long aadharNumber = approveList.getAadharNo();
					session.setAttribute("AadharsNo", aadharNumber);

					approveListMap.put("reasons", approveList.getReason());
					String reason = approveList.getReason();
					session.setAttribute("Reason", reason);

					approveListMap.put("From_district", approveList.getFromDistrict());
					String fromDistrict = approveList.getFromDistrict();
					session.setAttribute("From_District", fromDistrict);

					approveListMap.put("To_district", approveList.getToDistrict());
					String toDistrict = approveList.getToDistrict();
					session.setAttribute("To_District", toDistrict);

					approveListMap.put("travel_Date", approveList.getTravelDate());
					Date travelDate = approveList.getTravelDate();
					session.setAttribute("Travel_Date", travelDate);

					approveListMap.put("return_Date", approveList.getReturnDate());
					Date returnDate = approveList.getReturnDate();
					session.setAttribute("Return_Date", returnDate);

					approveListMap.put("no_of_passenger", approveList.getNoOfPassengers());
					Integer passengers = approveList.getNoOfPassengers();
					session.setAttribute("No_of_passengers", passengers);

					approveListMap.put("vehicle_number", approveList.getVehicleNo());
					String vehicleNo = approveList.getVehicleNo();
					session.setAttribute("VehicleNo", vehicleNo);

					approveListMap.put("phone_No", approveList.getMobileNo());
					Long mobileNo = approveList.getMobileNo();
					session.setAttribute("Mobile_No", mobileNo);

					approveListMap.put("actions", approveList.getStatus());
					String status = approveList.getStatus();
					session.setAttribute("Status", status);
					epassGetList.add(approveListMap);

					String sql1 = "Select application_no,applicant_name,ticket_no,aadhar_no,mobile_no,source,destination,travel_date,return_date,reason,no_of_passengers,status from travel_history where application_no=?";
					List<TravelEpassForm> travelPassView = jdbcTemplate.query(sql1, new GetTravelPassMapper(),
							aadharNo);
					List<Map<String, Object>> travelApproveList = new ArrayList<>();
					for (TravelEpassForm travelApprove : travelPassView) {
						Map<String, Object> travelApproveListMap = new HashMap<>();

						travelApproveListMap.put("application_number", travelApprove.getApplicationNumber());
						Long applicationNumber = travelApprove.getApplicationNumber();
						session.setAttribute("Application_Number", applicationNumber);

						travelApproveListMap.put("Applicant name", travelApprove.getApplicantName1());
						String applicantName = travelApprove.getApplicantName1();
						session.setAttribute("ApplicantName", applicantName);

						travelApproveListMap.put("Ticket_Number", travelApprove.getTicketNo());
						String ticketNumber = travelApprove.getTicketNo();
						session.setAttribute("TicketNo", ticketNumber);

						travelApproveListMap.put("Aadhar_No", travelApprove.getAadharNumber());
						Long aadharNo1 = travelApprove.getAadharNumber();
						session.setAttribute("AadharNo", aadharNo1);

						travelApproveListMap.put("Mobile_No", travelApprove.getPhoneNumber());
						Long mobileNumber = travelApprove.getPhoneNumber();
						session.setAttribute("MobileNo", mobileNumber);

						travelApproveListMap.put("source1", travelApprove.getSource());
						String source = travelApprove.getSource();
						session.setAttribute("Source", source);

						travelApproveListMap.put("destination1", travelApprove.getDestination());
						String destination = travelApprove.getDestination();
						session.setAttribute("Destination", destination);

						travelApproveListMap.put("from_Date", travelApprove.getFromDate());
						Date fromDate = travelApprove.getFromDate();
						session.setAttribute("From_Date", fromDate);

						travelApproveListMap.put("to_Date", travelApprove.getToDate());
						Date toDate = travelApprove.getFromDate();
						session.setAttribute("To_Date", toDate);

						travelApproveListMap.put("reasons1", travelApprove.getReasons());
						String reasons = travelApprove.getReasons();
						session.setAttribute("Reasons", reasons);

						travelApproveListMap.put("passengers", travelApprove.getPassengers());
						Integer passenger = travelApprove.getPassengers();
						session.setAttribute("Passengers", passenger);

						travelApproveListMap.put("status1", travelApprove.getAction());
						String action = travelApprove.getAction();
						session.setAttribute("Status1", action);

						travelApproveList.add(travelApproveListMap);

						logger.info("Login Successfully");
						session.setAttribute("aadhar", aadharNo);
						session.setAttribute("MobileNo", mNo);

					}
				}
			} else {
				return false;
			}

			if (aNo.equals(aadharNo)) {
				logger.info("Login Successfully");
				return true;
			}

			else {
				return false;
			}
	}
	
	public void epassApplyForm(EpassForm epass, HttpSession session) {
		logger.info("To apply Epass");
		String apply = "Insert into epass_apply(applicant_name,aadhar_no,gender,reason,father_name ,dob, from_district ,to_district ,state ,travel_date ,return_date,no_of_passengers ,vehicle_no, mobile_no ,vaccination_certificate,status,Certificate_image)values(?,?,?,?,?,?,?,?,'Tamilnadu',?,?,?,?,?,?,'Pending',?)";

		logger.info(apply);
		Object[] params = { epass.getApplicantName(), epass.getAadharNo(), epass.getGender(), epass.getReason(),
				epass.getFatherName(), epass.getDob(), epass.getFromDistrict(), epass.getToDistrict(),
				epass.getTravelDate(), epass.getReturnDate(), epass.getNoOfPassengers(), epass.getVehicleNo(),
				epass.getMobileNo(), epass.getVaccinationCertificate(), epass.getImage() };
		jdbcTemplate.update(apply, params);
		session.setAttribute("UserName", epass.getApplicantName());
		session.setAttribute("AadharNo", epass.getAadharNo());
		session.setAttribute("MobileNo", epass.getMobileNo());

	}

	public void travelPassApplyForm(TravelEpassForm travelPass, HttpSession session) {
		logger.info("To apply Epass");
		String travelPassApply = "insert into travel_history(applicant_name,gender,father_name,ticket_no,dob,aadhar_no,mobile_no,source,destination,travel_date,return_date,reason,no_of_passengers,vaccination_certificate,status,Certificate_image)values(?,?,?,?,?,?,?,?,'Tamilnadu',?,?,?,?,?,'Pending',?)";
		Object[] params = { travelPass.getApplicantName1(), travelPass.getGender1(), travelPass.getFatherName1(),
				travelPass.getTicketNo(), travelPass.getDateOfBirth(), travelPass.getAadharNumber(),
				travelPass.getPhoneNumber(), travelPass.getSource(), travelPass.getFromDate(), travelPass.getToDate(),
				travelPass.getReasons(), travelPass.getPassengers(), travelPass.getCovidCertificate(),
				travelPass.getImages() };
		jdbcTemplate.update(travelPassApply, params);
		session.setAttribute("UserName", travelPass.getApplicantName1());
		session.setAttribute("AadharNo", travelPass.getAadharNumber());
		session.setAttribute("MobileNo", travelPass.getPhoneNumber());

	}

	public void userEpassView(EpassForm epass) {
		logger.info("To view the Epass");
		String epassView = "Select application_no, applicant_name,aadhar_no ,gender,reason,father_name ,dob, from_district ,to_district ,state ,travel_date ,return_date,no_of_passengers ,vehicle_no, mobile_no ,vaccination_certificate,status from epass_apply where application_no=?,status=?";
		Object[] params = { epass.getApplicationNo(), epass.getStatus() };
		jdbcTemplate.update(epassView, params);

	}

	public List<EpassForm> listEpassformImage(Integer appId) {
		String sql = "Select application_no, applicant_name,aadhar_no ,gender,reason,father_name ,dob, from_district ,to_district ,state ,travel_date ,return_date,no_of_passengers ,vehicle_no, mobile_no ,vaccination_certificate,status,Certificate_image from epass_apply where application_no=? order by application_no DESC";
		return jdbcTemplate.query(sql, new EpassFormMapper(), appId);
	}

	public List<TravelEpassForm> listTravelPassFormImage(Integer appId1) {
		String sql = "Select application_no,applicant_name,gender,father_name,ticket_no,dob,aadhar_no,mobile_no,source,destination,travel_date,return_date,reason,no_of_passengers,vaccination_certificate,status,Certificate_image from travel_history where application_no=? order by application_no DESC";
		return jdbcTemplate.query(sql, new TravelEpassFormMapper(), appId1);
	}

	public List<EpassForm> approveEpassformImage(Integer appNo) {
		String sql = "Select application_no, applicant_name,aadhar_no ,gender,reason,father_name ,dob, from_district ,to_district ,state ,travel_date ,return_date,no_of_passengers ,vehicle_no, mobile_no ,vaccination_certificate,status,Certificate_image from epass_apply where status='Approved' order by application_no DESC";
		return jdbcTemplate.query(sql, new EpassFormMapper(), appNo);
	}

	public List<EpassForm> userView(EpassForm epass, Model model) {
		Long applicationNo = epass.getApplicationNo();
		String sql = "Select status from epass_apply where application_no=?";
		List<EpassForm> epassStatusView = jdbcTemplate.query(sql, new EpassStatusMapper(), applicationNo);
		List<Map<String, Object>> epassList = new ArrayList<>();
		for (EpassForm epassStatus : epassStatusView) {
			Map<String, Object> epassStatusListMap = new HashMap<>();
			epassStatusListMap.put("status", epassStatus.getStatus());
			epassList.add(epassStatusListMap);

		}

		model.addAttribute("epassStatusView", epassList);
		return epassStatusView;
	}

	public List<TravelEpassForm> travelPassView(TravelEpassForm travelPass, Model model) {
		Long applicationNumber = travelPass.getApplicationNumber();
		String sql = "Select status from travel_history where application_no=?";
		List<TravelEpassForm> travelPassView = jdbcTemplate.query(sql, new TravelPassStausMapper(), applicationNumber);
		List<Map<String, Object>> travelList = new ArrayList<>();
		for (TravelEpassForm travelPassStatus : travelPassView) {
			Map<String, Object> travelStatusListMap = new HashMap<>();
			travelStatusListMap.put("action", travelPassStatus.getAction());
			travelList.add(travelStatusListMap);
		}
		model.addAttribute("TravelPassStatusView", travelList);
		return travelPassView;
	}

	public void forgotPassword(UserDetails register) {
		logger.info("update password");

		String query = "Update userDetails1 set password=? where user_name=?";
		Object[] upd = { register.getPassword(), register.getUserName() };
		jdbcTemplate.update(query, upd);
		logger.info("Row updated");

	}

	public void epassHistory(Model model, HttpSession session) throws JsonProcessingException {
		String applicantName = session.getAttribute("UserName").toString();

		String sql = "Select application_no, applicant_name,aadhar_no ,gender,reason,father_name ,dob, from_district ,to_district ,state ,travel_date ,return_date,no_of_passengers ,vehicle_no, mobile_no ,vaccination_certificate,status,Certificate_image from epass_apply where applicant_name=?";
		List<EpassForm> epassView = jdbcTemplate.query(sql, new EpassFormMapper(), applicantName);
		List<Map<String, Object>> epassList = new ArrayList<>();
		for (EpassForm list : epassView) {
			Map<String, Object> epassHistoryListMap = new HashMap<>();
			epassHistoryListMap.put("application_no", list.getApplicationNo());
			session.setAttribute("ApplicationNumber", list.getApplicationNo());

			epassHistoryListMap.put("applicant_name", list.getApplicantName());
			epassHistoryListMap.put("aadhar_no", list.getAadharNo());
			epassHistoryListMap.put("gender", list.getGender());
			epassHistoryListMap.put("reason", list.getReason());
			epassHistoryListMap.put("father_name", list.getFatherName());
			epassHistoryListMap.put("dob", list.getDob());
			epassHistoryListMap.put("from_district", list.getFromDistrict());
			epassHistoryListMap.put("to_district", list.getToDistrict());
			epassHistoryListMap.put("state", list.getState());
			epassHistoryListMap.put("travel_date", list.getTravelDate());
			epassHistoryListMap.put("return_date", list.getReturnDate());
			epassHistoryListMap.put("no_of_passengers", list.getNoOfPassengers());
			epassHistoryListMap.put("vehicle_no", list.getVehicleNo());
			epassHistoryListMap.put("mobile_no", list.getMobileNo());
			epassHistoryListMap.put("status", list.getStatus());
			epassHistoryListMap.put("vaccination_certificate", list.getVaccinationCertificate());
			epassHistoryListMap.put("Certificate_image", list.getImage());

			epassList.add(epassHistoryListMap);
		}
		ObjectMapper epassMapper = new ObjectMapper();
		String epassAction = epassMapper.writeValueAsString(epassList);
		model.addAttribute("userList", epassAction);
	}

	public void travelPassHistory(Model model, HttpSession session) throws JsonProcessingException {

		String applicantName = session.getAttribute("UserName").toString();
		String sql = "Select application_no,applicant_name,gender,father_name,ticket_no,dob,aadhar_no,mobile_no,source,destination,travel_date,return_date,reason,no_of_passengers,vaccination_certificate,status,Certificate_image from travel_history where applicant_name=?";
		List<TravelEpassForm> travelPassView = jdbcTemplate.query(sql, new TravelEpassFormMapper(), applicantName);
		List<Map<String, Object>> travelList = new ArrayList<>();
		for (TravelEpassForm travel : travelPassView) {
			Map<String, Object> travel_historyListMap = new HashMap<>();
			travel_historyListMap.put("application_no", travel.getApplicationNumber());
			session.setAttribute("Application_Number", travel.getApplicationNumber());

			travel_historyListMap.put("applicant_name", travel.getApplicantName1());
			travel_historyListMap.put("gender", travel.getGender1());
			travel_historyListMap.put("father_name", travel.getFatherName1());
			travel_historyListMap.put("ticket_no", travel.getTicketNo());
			travel_historyListMap.put("dob", travel.getDateOfBirth());
			travel_historyListMap.put("aadhar_no", travel.getAadharNumber());
			travel_historyListMap.put("mobile_no", travel.getPhoneNumber());
			travel_historyListMap.put("source", travel.getSource());
			travel_historyListMap.put("destination", travel.getDestination());
			travel_historyListMap.put("travel_date", travel.getFromDate());
			travel_historyListMap.put("return_date", travel.getToDate());
			travel_historyListMap.put("reason", travel.getReasons());
			travel_historyListMap.put("no_of_passengers", travel.getPassengers());
			travel_historyListMap.put("vaccination_certificate", travel.getCovidCertificate());
			travel_historyListMap.put("status", travel.getAction());
			travel_historyListMap.put("Certificate_image", travel.getImages());

			travelList.add(travel_historyListMap);

		}
		ObjectMapper travelMapper = new ObjectMapper();
		String travelAction = travelMapper.writeValueAsString(travelList);
		model.addAttribute("viewList", travelAction);

	}

	public void userProfile(UserDetails register, HttpSession session) {

		String name = session.getAttribute("UserName").toString();
		String sql1 = "Update user_details set aadhar_no=?,mail_id=?,password=?,mobile_no=? where user_name=?";
		Object[] params = { register.getAadharNo(), register.getMailId(), register.getPassword(),
				register.getMobileNo(), name };
		jdbcTemplate.update(sql1, params);

	}

	public void userProfileView(HttpSession session) {
		Long aadharNumber = (Long) session.getAttribute("AadharNo");
		String profile = "Select aadhar_no,user_name,mail_id,password,mobile_no from user_details";
		List<UserDetails> userProfile = jdbcTemplate.query(profile, new ProfileMapper());
		List<UserDetails> userProfile1=userProfile.stream().filter(profiles -> profiles.getAadharNo().equals(aadharNumber)).toList();

		for (UserDetails details : userProfile1) {

			Long aadharNo = details.getAadharNo();
			session.setAttribute("AadharNo", aadharNo);

			String userName = details.getUserName();
			session.setAttribute("UserName", userName);

			String mailId = details.getMailId();
			session.setAttribute("MailId", mailId);

			String password = details.getPassword();
			session.setAttribute("Password", password);

			Long mobileNo = details.getMobileNo();
			session.setAttribute("MobileNumber", mobileNo);

		}

	}

	public List<EpassForm> getEpass(HttpSession session) {
		Long applicationNumber = (Long) session.getAttribute("ApplicationNumber");

		String sql = "Select application_no, applicant_name,aadhar_no ,reason,from_district ,to_district,travel_date ,return_date,no_of_passengers,vehicle_no, mobile_no,status from epass_apply where application_no=?";
		List<EpassForm> getEpass = jdbcTemplate.query(sql, new GetEpassMapper(), applicationNumber);
		List<Map<String, Object>> epassGetList = new ArrayList<>();
		for (EpassForm approveList : getEpass) {
			Map<String, Object> approveListMap = new HashMap<>();

			approveListMap.put("Application No", approveList.getApplicationNo());
			Long applicationNo = approveList.getApplicationNo();

			session.setAttribute("ApplicationNumber", applicationNo);

			approveListMap.put("applicant_Name", approveList.getApplicantName());
			String name = approveList.getApplicantName();
			session.setAttribute("Applicant_Name", name);

			approveListMap.put("aadhar_number", approveList.getAadharNo());
			Long aadharNumber = approveList.getAadharNo();
			session.setAttribute("AadharsNo", aadharNumber);

			approveListMap.put("reasons", approveList.getReason());
			String reason = approveList.getReason();
			session.setAttribute("Reason", reason);

			approveListMap.put("From_district", approveList.getFromDistrict());
			String fromDistrict = approveList.getFromDistrict();
			session.setAttribute("From_District", fromDistrict);

			approveListMap.put("To_district", approveList.getToDistrict());
			String toDistrict = approveList.getToDistrict();
			session.setAttribute("To_District", toDistrict);

			approveListMap.put("travel_Date", approveList.getTravelDate());
			Date travelDate = approveList.getTravelDate();
			session.setAttribute("Travel_Date", travelDate);

			approveListMap.put("return_Date", approveList.getReturnDate());
			Date returnDate = approveList.getReturnDate();
			session.setAttribute("Return_Date", returnDate);

			approveListMap.put("no_of_passenger", approveList.getNoOfPassengers());
			Integer passengers = approveList.getNoOfPassengers();
			session.setAttribute("No_of_passengers", passengers);

			approveListMap.put("vehicle_number", approveList.getVehicleNo());
			String vehicleNo = approveList.getVehicleNo();
			session.setAttribute("VehicleNo", vehicleNo);

			approveListMap.put("phone_No", approveList.getMobileNo());
			Long mobileNo = approveList.getMobileNo();
			session.setAttribute("Mobile_No", mobileNo);

			approveListMap.put("actions", approveList.getStatus());
			String status = approveList.getStatus();
			session.setAttribute("Status", status);

			epassGetList.add(approveListMap);
		}

		return getEpass;
	}

	public List<TravelEpassForm> getTravelPassForm(HttpSession session) {
		Long applicationNo = (Long) session.getAttribute("Application_Number");
		String sql1 = "Select application_no,applicant_name,ticket_no,aadhar_no,mobile_no,source,destination,travel_date,return_date,reason,no_of_passengers,status from travel_history where application_no=?";
		List<TravelEpassForm> travelPassView = jdbcTemplate.query(sql1, new GetTravelPassMapper(), applicationNo);
		List<Map<String, Object>> travelApproveList = new ArrayList<>();
		for (TravelEpassForm travelApprove : travelPassView) {
			Map<String, Object> travelApproveListMap = new HashMap<>();

			travelApproveListMap.put("application_number", travelApprove.getApplicationNumber());
			Long applicationNumber = travelApprove.getApplicationNumber();
			session.setAttribute("Application_Number", applicationNumber);

			travelApproveListMap.put("Applicant name", travelApprove.getApplicantName1());
			String applicantName = travelApprove.getApplicantName1();
			session.setAttribute("ApplicantName", applicantName);

			travelApproveListMap.put("Ticket_Number", travelApprove.getTicketNo());
			String ticketNumber = travelApprove.getTicketNo();
			session.setAttribute("Ticket_No", ticketNumber);

			travelApproveListMap.put("Aadhar_No", travelApprove.getAadharNumber());
			Long aadharNo1 = travelApprove.getAadharNumber();
			session.setAttribute("AadharNo", aadharNo1);

			travelApproveListMap.put("Mobile_No", travelApprove.getPhoneNumber());
			Long mobileNumber = travelApprove.getPhoneNumber();
			session.setAttribute("MobileNo", mobileNumber);

			travelApproveListMap.put("source1", travelApprove.getSource());
			String source = travelApprove.getSource();
			session.setAttribute("Source", source);

			travelApproveListMap.put("destination1", travelApprove.getDestination());
			String destination = travelApprove.getDestination();
			session.setAttribute("Destination", destination);

			travelApproveListMap.put("from_Date", travelApprove.getFromDate());
			Date fromDate = travelApprove.getFromDate();
			session.setAttribute("From_Date", fromDate);

			travelApproveListMap.put("to_Date", travelApprove.getToDate());
			Date toDate = travelApprove.getFromDate();
			session.setAttribute("To_Date", toDate);

			travelApproveListMap.put("reasons1", travelApprove.getReasons());
			String reasons = travelApprove.getReasons();
			session.setAttribute("Reasons", reasons);

			travelApproveListMap.put("passengers", travelApprove.getPassengers());
			Integer passenger = travelApprove.getPassengers();
			session.setAttribute("Passengers", passenger);

			travelApproveListMap.put("status1", travelApprove.getAction());
			String action = travelApprove.getAction();
			session.setAttribute("Status1", action);

			travelApproveList.add(travelApproveListMap);

		}
		return travelPassView;
	}

}
