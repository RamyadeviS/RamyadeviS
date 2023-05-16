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
import com.chainsys.epassproject.model.TravelEpassForm;
import com.chainsys.epassproject.service.EpassService;
import com.chainsys.epassproject.validation.ValidationUser;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class TravelPassController {
	EpassDao registerDao = new EpassDao();

	ValidationUser valid = new ValidationUser();
	EpassService epassService = new EpassService();
	Logger logger = LoggerFactory.getLogger(TravelPassController.class);

	private static final String POPUPMESSAGE1 = "errorMessage1";
	private static final String POPUPMESSAGE = "errorMessage";
	private static final String ERRORMESSAGE = "ErrorMessage";

	@RequestMapping("/travelPassApply")
	public String travelFormApply(@ModelAttribute("Apply") TravelEpassForm travelPass, HttpSession session) {
		return "TravelPassForm";
	}

	@PostMapping("/travelForm")
	public String travelForm(@RequestParam("VaccineImage") MultipartFile image,
			@ModelAttribute("Apply") TravelEpassForm travelPass, HttpSession session, Model model)
			throws IOException {

		String fileName = image.getOriginalFilename();
		try (FileInputStream file = new FileInputStream(
				"C:\\Users\\ramy3344\\eclipse-workspace\\E-pass\\src\\main\\resources\\static\\Images\\" + fileName);) {
			byte[] images = file.readAllBytes();
			travelPass.setImages(images);
		}

		for (int i = 1; i <= 20; i++) {
			session.removeAttribute(POPUPMESSAGE1 + i);
		}

		if (Boolean.FALSE.equals(ValidationUser.nameValidation(travelPass.getFatherName1(), model))
				|| Boolean.FALSE.equals(valid.dateValidation(travelPass.getDateOfBirth(), model))
				|| Boolean.FALSE.equals(ValidationUser.nameValidation(travelPass.getSource(), model))
				|| Boolean.FALSE.equals(valid.dateValidation(travelPass.getFromDate(), model))
				|| Boolean.FALSE.equals(valid.dateValidation(travelPass.getToDate(), model))
				|| Boolean.FALSE.equals(valid.noOfPassengersValidation(travelPass.getPassengers(), model))
				|| Boolean.FALSE.equals(ValidationUser.mobileNoValidation(travelPass.getPhoneNumber(), model))
				|| Boolean.FALSE.equals(ValidationUser.ticketNoValidation(travelPass.getTicketNo(), model)))

		{

			for (int j = 1; j <= 20; j++) {
				if (model.getAttribute(POPUPMESSAGE + j) != null) {
					String errorMessage = (String) model.getAttribute(POPUPMESSAGE + j);
					model.addAttribute(ERRORMESSAGE, errorMessage);
				}
			}
			return "ErrorPopup.html";
		}

		epassService.travelPassApply(travelPass, session);

		return "TravelPassFormSuccess.html";

	}

	@GetMapping("/travelView")
	public String view(@ModelAttribute("applicationNumber") TravelEpassForm travelPass, Model model,
			HttpSession session) throws JsonProcessingException {
		logger.info("To get the status");
		epassService.viewTravelPass(travelPass, model, session);
		return "TravelPassStatusView.html";

	}

	@GetMapping("/getTravelPass")
	public String getTravelPass(@ModelAttribute("getTravel") TravelEpassForm travelPass, HttpSession session,
			Model model) {
		epassService.downloadTravelPass(session);
		String statusApproved = (String) session.getAttribute("Status1");
		for (int i = 1; i <= 20; i++) {
			session.removeAttribute(POPUPMESSAGE1 + i);
		}

		if (Boolean.FALSE.equals(valid.checkTravelStatus(statusApproved, model)))

		{

			for (int j = 1; j <= 20; j++) {
				if (model.getAttribute(POPUPMESSAGE + j) != null) {
					String errorMessage = (String) model.getAttribute(POPUPMESSAGE + j);
					model.addAttribute(ERRORMESSAGE, errorMessage);
				}
			}
			return "DownloadFailed.html";
		} else {

			return "TravelPassDownload.html";
		}
	}
}
