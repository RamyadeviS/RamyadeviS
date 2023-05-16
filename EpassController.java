package com.chainsys.epassproject.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chainsys.epassproject.dao.EpassDao;
import com.chainsys.epassproject.model.EpassForm;
import com.chainsys.epassproject.model.UserDetails;
import com.chainsys.epassproject.service.EpassService;
import com.chainsys.epassproject.validation.ValidationUser;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class EpassController {

	EpassDao registerDao = new EpassDao();

	EpassService epassService = new EpassService();
	ValidationUser valid = new ValidationUser();
	Logger logger = LoggerFactory.getLogger(EpassController.class);

	private static final String USERLOGIN = "UserLogin.html";
	private static final String POPUPMESSAGE1 = "errorMessage1";
	private static final String POPUPMESSAGE = "errorMessage";
	private static final String ERRORMESSAGE = "ErrorMessage";

	@RequestMapping("/")
	public String dash() {
		return "DashBoard";

	}

	@GetMapping("/aboutUs")
	public String about() {
		return "AboutUsForm.html";

	}

	@GetMapping("/contactUs")
	public String contact() {
		return "ContactUsForm";
	}

	@RequestMapping("/userDashboard")
	public String userDashboard() {
		return "UserDashboard.html";

	}

	@GetMapping("/signUp")
	public String registerEpass(HttpSession session, @ModelAttribute("sign") UserDetails register, Model model) {
		return "UserDetails.html";

	}

	@GetMapping("/register")
	public String register(HttpSession session, @ModelAttribute("sign") UserDetails register, Model model,
			Long aadharNo, String mailId) {
		logger.info("Register successfully");

		for (int i = 1; i <= 20; i++) {
			session.removeAttribute(POPUPMESSAGE1 + i);
		}

		if (Boolean.FALSE.equals(ValidationUser.nameValidation(register.getUserName(), model))
				|| Boolean.FALSE.equals(valid.aadharNoValidation(register.getAadharNo(), model))
				|| Boolean.FALSE.equals(registerDao.aadharNoExisting(register.getAadharNo(), model))

				|| Boolean.FALSE.equals(ValidationUser.mailIdValidation(register.getMailId(), model))
				|| Boolean.FALSE.equals(registerDao.mailIdExisting(register.getMailId(), model))
				|| Boolean.FALSE.equals(ValidationUser.passwordValidation(register.getPassword(), model))
				|| Boolean.FALSE.equals(ValidationUser.mobileNoValidation(register.getMobileNo(), model)))

		{

			for (int j = 1; j <= 20; j++) {
				if (model.getAttribute(POPUPMESSAGE + j) != null) {
					String errorMessage = (String) model.getAttribute(POPUPMESSAGE + j);
					model.addAttribute(ERRORMESSAGE, errorMessage);
				}
			}
			return "ErrorPopup.html";
		}
		epassService.registerUser(register, session);
		return "RegisterSuccessfully.html";

	}

	@GetMapping("/userLoginEpass")
	public String loginUserEpass(HttpSession session, @ModelAttribute("signIn") UserDetails register) {
		return USERLOGIN;

	}

	@GetMapping("/userLogin")
	public String loginUser(HttpSession session, Model model, @ModelAttribute("signIn") UserDetails register) {
		logger.info("Login successfully");

		if (Boolean.TRUE.equals(epassService.login(register, session))) {

			registerDao.getEpass(session);
			registerDao.getTravelPassForm(session);

			return "LoginSuccess.html";

		} else if (Boolean.FALSE.equals(epassService.login(register, session))) {
			return USERLOGIN;

		}
		return USERLOGIN;
	}

	@RequestMapping("/EpassApply")
	public String epassFormApply(@ModelAttribute("FormApply") EpassForm epass, HttpSession session) {
		return "EpassApplyForm.html";
	}

	@PostMapping("/epassForm")
	public String epassForm(@RequestParam("VaccineImage") MultipartFile image,
			@ModelAttribute("FormApply") EpassForm epass, HttpSession session, Model model)
			throws IOException {

		String fileName = image.getOriginalFilename();
		try (FileInputStream file = new FileInputStream(
				"C:\\Users\\ramy3344\\eclipse-workspace\\E-pass\\src\\main\\resources\\static\\Images\\" + fileName);) {

			byte[] images = file.readAllBytes();
			epass.setImage(images);
		}
		for (int i = 1; i <= 20; i++) {
			session.removeAttribute(POPUPMESSAGE1 + i);
		}

		if (Boolean.FALSE.equals(ValidationUser.nameValidation(epass.getFatherName(), model))
				|| Boolean.FALSE.equals(valid.dateValidation(epass.getDob(), model))
				|| Boolean.FALSE.equals(valid.dateValidation(epass.getTravelDate(), model))
				|| Boolean.FALSE.equals(valid.dateValidation(epass.getReturnDate(), model))
				|| Boolean.FALSE.equals(valid.noOfPassengersValidation(epass.getNoOfPassengers(), model))
				|| Boolean.FALSE.equals(ValidationUser.mobileNoValidation(epass.getMobileNo(), model))
				|| Boolean.FALSE.equals(ValidationUser.vehicleNoValidation(epass.getVehicleNo(), model)))

		{

			for (int j = 1; j <= 20; j++) {
				if (model.getAttribute(POPUPMESSAGE + j) != null) {
					String errorMessage = (String) model.getAttribute(POPUPMESSAGE + j);
					model.addAttribute(ERRORMESSAGE, errorMessage);
				}
			}
			return "ErrorPopup.html";
		}
		if (valid.districtMatch(epass.getFromDistrict(), epass.getToDistrict()).equals(true)) {
			epassService.epassFormApply(epass, session);
			return "EpassFormSuccess.html";
		} else {
			return "SameDistrict.html";
		}
	}

	@GetMapping("/userView")
	public String userView(@ModelAttribute("applicationNo") EpassForm epass, Model model, HttpSession session)
			throws JsonProcessingException {
		logger.info("To get the status");
		epassService.epassView(epass, model, session);
		return "EpassStatusView.html";

	}

	@GetMapping("/forgot")
	public String updatePassword(@ModelAttribute("Forgot") UserDetails register) {
		return "ForgotPassword.html";

	}

	@GetMapping("/forgotPassword")
	public String updPassword(@ModelAttribute("Forgot") UserDetails register) {
		epassService.updatePassword(register);
		return USERLOGIN;

	}

	@GetMapping("/epassHistory")
	public String travelHistory(@ModelAttribute("history") EpassForm epass, Model model, HttpSession session)
			throws JsonProcessingException {
		epassService.userTravelHistory(epass, model, session);

		return "EpassHistory.html";
	}

	@GetMapping("/profile")
	public String profileForm(@ModelAttribute("profile") UserDetails register, HttpSession session) {
		epassService.profie(register, session);
		return "Profile.html";
	}

	@GetMapping("/profileView")
	public String profileFormView(@ModelAttribute("profileView") UserDetails register, HttpSession session) {
		epassService.profieView(register, session);
		return "UserDashboard.html";
	}

	@GetMapping("/getEpass")
	public String getEpass(@ModelAttribute("epassGet") EpassForm epass, HttpSession session, Model model) {
		epassService.downloadEpass(session);
		String statusApprove = (String) session.getAttribute("Status");
		for (int i = 1; i <= 20; i++) {
			session.removeAttribute(POPUPMESSAGE1 + i);
		}

		if (Boolean.FALSE.equals(valid.checkStatus(statusApprove, model)))

		{

			for (int j = 1; j <= 20; j++) {
				if (model.getAttribute(POPUPMESSAGE + j) != null) {
					String errorMessage = (String) model.getAttribute(POPUPMESSAGE + j);
					model.addAttribute(ERRORMESSAGE, errorMessage);
				}
			}
			return "DownloadFailed.html";
		} else {
			return "EpassDownload.html";
		}
	}
}
