package com.chainsys.epassproject.controller;

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

import com.chainsys.epassproject.dao.AdminDao;
import com.chainsys.epassproject.model.AdminLogin;
import com.chainsys.epassproject.model.EpassForm;
import com.chainsys.epassproject.model.TravelEpassForm;
import com.chainsys.epassproject.model.UserDetails;
import com.chainsys.epassproject.service.AdminService;
import com.chainsys.epassproject.service.EpassService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller

public class AdminController {
	AdminDao adminDao = new AdminDao();

	EpassService epassService = new EpassService();
	AdminService adminService = new AdminService();

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@RequestMapping("/adminDashboard")
	public String dashBoard(Model model) throws JsonProcessingException {
		adminService.getEpassList(model);
		adminService.getTravelpassList(model);
		adminService.epassCount(model);

		adminService.travelPassCount(model);

		adminService.totalCount(model);

		adminService.epassApproveCount(model);

		adminService.epassRejectCount(model);

		adminService.travelPassApproveCount(model);

		adminService.travelRejectCount(model);

		adminService.epassPendingCount(model);

		adminService.travelPendingCount(model);
		return "AdminDashboard.html";

	}

	@GetMapping("/login")
	public String admin(Model model, @ModelAttribute("admin") UserDetails register,
			@ModelAttribute("epass") EpassForm epass, @ModelAttribute("travel") TravelEpassForm travelPass) {
		return "Admin";
	}

	@GetMapping("/adminLogin")
	public String adminLogin(Model model, @ModelAttribute("admin") UserDetails register,
			@ModelAttribute("epass") EpassForm epass, @ModelAttribute("travel") TravelEpassForm travelPass) throws JsonProcessingException {

		adminService.epassCount(model);

		adminService.travelPassCount(model);

		adminService.totalCount(model);

		adminService.epassApproveCount(model);

		adminService.epassRejectCount(model);

		adminService.travelPassApproveCount(model);

		adminService.travelRejectCount(model);

		adminService.epassPendingCount(model);

		adminService.travelPendingCount(model);
        
		if (Boolean.TRUE.equals(adminService.loginAdmin(register, model))) {
			adminService.getEpassList(model);
			adminService.getTravelpassList(model);

			return "AdminDashboard.html";

		} else if (Boolean.FALSE.equals(adminService.loginAdmin(register, model))) {
			return "Admin.html";

		}
		return "Admin.html";

	}

	@GetMapping("/listEpassForm")
	public String getEpassFormUser(@ModelAttribute("epass") EpassForm epass, Model model, HttpSession session)
			throws JsonProcessingException {
		adminService.getEpassList(model);
		return "EpassListView.html";
	}

	@PostMapping("/listEpassFormImage")

	public String getEpassFormUserImage(@RequestParam("imageView") Integer appId, Model model) {
		epassService.getEpassFormImage(appId, model);
		return "ImageView.html";
	}

	@PostMapping("/epassApproveImage")
	public String getEpassApproveImage(@RequestParam("approveImageView") Integer appId, Model model) {
		epassService.epassApproveImageGet(appId, model);
		return "ApproveImageView.html";
	}

	@GetMapping("/update")
	public String updateStatus(@ModelAttribute("epass") EpassForm epass) {
		logger.info("to update the status");
		adminService.epassApproved(epass);
		return "EpassApprove.html";
	}

	@GetMapping("/reject")
	public String rejectStatus(@ModelAttribute("epass") EpassForm epass) {
		logger.info("To reject the status");
		adminService.epassReject(epass);
		return "EpassReject.html";

	}

	@GetMapping("/listTravelPass")
	public String getListTravelPass(@ModelAttribute("travel") TravelEpassForm travelPass, Model model,
			HttpSession session) throws JsonProcessingException {
		adminService.getTravelpassList(model);
		return "TravelListView.html";

	}

	@PostMapping("/listTravelPassFormImage")
	public String getTravelPassFormUserImage(@RequestParam("travelImgView") Integer appId1, Model model) {
		epassService.getTravelPassFormImage(appId1, model);
		return "TravelImageView.html";
	}

	@GetMapping("/approve")
	public String approvedStatus(@ModelAttribute("travel") TravelEpassForm travelPass) {
		logger.info("To update the status");
		adminService.travelPassApproved(travelPass);
		return "TravelPassApprove.html";

	}

	@GetMapping("/travelReject")
	public String travelRejectStatus(@ModelAttribute("travel") TravelEpassForm travelPass) {
		logger.info("To update the status");
		adminService.travelPassReject(travelPass);
		return "TravelPassReject.html";
	}
}


