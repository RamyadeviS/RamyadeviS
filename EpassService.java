package com.chainsys.epassproject.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.chainsys.epassproject.dao.AdminDao;
import com.chainsys.epassproject.dao.EpassDao;
import com.chainsys.epassproject.model.AdminLogin;
import com.chainsys.epassproject.model.EpassForm;
import com.chainsys.epassproject.model.TravelEpassForm;
import com.chainsys.epassproject.model.UserDetails;
import com.chainsys.epassproject.myexception.InvalidAadharNoException;
import com.chainsys.epassproject.myexception.InvalidPwdException;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class EpassService {
	AdminDao adminDao = new AdminDao();
	EpassDao registerDao = new EpassDao();

	public void registerUser(UserDetails register, HttpSession session) {
		registerDao.userRegister(register, session);
	}

	public boolean login(UserDetails register, HttpSession session) {
		return registerDao.userLogin(register, session);
	}

	public void epassFormApply(EpassForm epass, HttpSession session) {
		registerDao.epassApplyForm(epass, session);
	}

	public void travelPassApply(TravelEpassForm travelPass, HttpSession session) {
		registerDao.travelPassApplyForm(travelPass, session);
	}

	public void updatePassword(@ModelAttribute("Forgot") UserDetails register) {
		registerDao.forgotPassword(register);
	}

	public void userTravelHistory(@ModelAttribute("history") EpassForm epass, Model model, HttpSession session)
			throws JsonProcessingException {
		registerDao.epassHistory(model, session);
		registerDao.travelPassHistory(model, session);
	}

	public void profie(@ModelAttribute("profile") UserDetails register, HttpSession session) {
		registerDao.userProfileView(session);
	}

	public void profieView(@ModelAttribute("profileView") UserDetails register, HttpSession session) {

		registerDao.userProfile(register, session);
	}

	public List<EpassForm> downloadEpass(@ModelAttribute("epassGet") HttpSession session) {
		return registerDao.getEpass(session);
	}

	public void downloadTravelPass(@ModelAttribute("getTravel") HttpSession session) {
		registerDao.getTravelPassForm(session);
	}

	public void getEpassFormImage(@RequestParam("imageView") Integer appId, Model model) {
		List<EpassForm> list = registerDao.listEpassformImage(appId);
		model.addAttribute("adminDashboard", list);
	}

	public void epassView(EpassForm epass, Model model, HttpSession session) throws JsonProcessingException {
		adminDao.listEpassform(model);
		List<EpassForm> status = registerDao.userView(epass, model);
		session.setAttribute("EpassStatus_List", status);
	}

	public void viewTravelPass(TravelEpassForm travelPass, Model model, HttpSession session)
			throws JsonProcessingException {

		adminDao.listTravelPassForm(model);
		List<TravelEpassForm> action = registerDao.travelPassView(travelPass, model);
		session.setAttribute("TravelStatus_List", action);

	}

	public void epassApproveImageGet(@RequestParam("approveImageView") Integer appId, Model model) {
		List<EpassForm> list = registerDao.approveEpassformImage(appId);
		model.addAttribute("epassApprove", list);
	}

	public void getTravelPassFormImage(@RequestParam("travelImgView") Integer appId1, Model model) {
		List<TravelEpassForm> list = registerDao.listTravelPassFormImage(appId1);
		model.addAttribute("travelPass", list);
	}

}