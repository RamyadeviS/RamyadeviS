package com.chainsys.epassproject.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.chainsys.epassproject.model.AdminLogin;
import com.chainsys.epassproject.model.EpassForm;
import com.chainsys.epassproject.model.TravelEpassForm;
import com.chainsys.epassproject.model.UserDetails;
import com.chainsys.epassproject.myexception.InvalidPwdException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface EpassInterface {
	public void userRegister(UserDetails register, HttpSession session);

	public Boolean mailIdExisting(String mailId, Model model);

	public Boolean aadharNoExisting(Long aadharNo, Model model);

	public boolean userLogin(UserDetails register, HttpSession session);

	public void epassApplyForm(EpassForm epass, HttpSession session);

	public void travelPassApplyForm(TravelEpassForm travelPass, HttpSession session);

	public void userEpassView(EpassForm epass);

	public List<EpassForm> userView(EpassForm epass, Model model);

	public List<TravelEpassForm> travelPassView(TravelEpassForm travelPass, Model model);

	public void forgotPassword(UserDetails register);

	public void epassHistory(Model model, HttpSession session) throws JsonProcessingException;

	public void travelPassHistory(Model model, HttpSession session) throws JsonProcessingException;

	public void userProfile(UserDetails register, HttpSession session);

	public void userProfileView(HttpSession session);

	public List<EpassForm> getEpass(HttpSession session);

	public List<TravelEpassForm> getTravelPassForm(HttpSession session);

}
