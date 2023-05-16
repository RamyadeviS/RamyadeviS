package com.chainsys.epassproject.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.chainsys.epassproject.model.AdminLogin;
import com.chainsys.epassproject.model.EpassForm;
import com.chainsys.epassproject.model.TravelEpassForm;
import com.chainsys.epassproject.model.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AdminInterface {

	public boolean adminLogin(UserDetails register, Model model);

	public void listEpassform(Model model) throws JsonProcessingException;

	public void epassStatusUpdate(EpassForm epass);

	public void epassStatusReject(EpassForm epass);

	public List<EpassForm> listEpassformImage(Integer appId);

	public Integer totalEpassCount();

	public Integer totalTravelPassCount();

	public Integer totalUserCount();

	public Integer approvedCount();

	public Integer rejectedCount();

	public Integer approveCount();

	public Integer rejectCount();

	public Integer pendingEpassCount();

	public Integer pendingCount();

	public void listTravelPassForm(Model model) throws JsonProcessingException;

	public void travelStatusApprove(TravelEpassForm travelPass);

	public void travelStatusReject(TravelEpassForm travelPass);

	public List<TravelEpassForm> listTravelPassFormImage(Integer appId1);
}
