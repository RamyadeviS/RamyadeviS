package com.chainsys.epassproject.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.chainsys.epassproject.dao.AdminDao;
import com.chainsys.epassproject.dao.EpassDao;
import com.chainsys.epassproject.model.AdminLogin;
import com.chainsys.epassproject.model.EpassForm;
import com.chainsys.epassproject.model.TravelEpassForm;
import com.chainsys.epassproject.model.UserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;

public class AdminService {
	EpassDao registerDao = new EpassDao();
	AdminDao adminDao = new AdminDao();

	public boolean loginAdmin(UserDetails register, Model model) {
		return adminDao.adminLogin(register, model);
	}

	public void getEpassList(Model model) throws JsonProcessingException {

		adminDao.listEpassform(model);
	}

	public void getTravelpassList(Model model) throws JsonProcessingException {
		adminDao.listTravelPassForm(model);
	}

	public void epassApproved(EpassForm epass) {
		adminDao.epassStatusUpdate(epass);
	}

	public void epassReject(EpassForm epass) {
		adminDao.epassStatusReject(epass);

	}

	public void travelPassApproved(TravelEpassForm travelPass) {
		adminDao.travelStatusApprove(travelPass);
	}

	public void travelPassReject(TravelEpassForm travelPass) {
		adminDao.travelStatusReject(travelPass);
	}

	public void epassCount(Model model) {
		Integer epassCount = adminDao.totalEpassCount();
		model.addAttribute("Epass_count", epassCount);
	}

	public void travelPassCount(Model model) {
		Integer travelPassCount = adminDao.totalTravelPassCount();
		model.addAttribute("TravelPass_count", travelPassCount);
	}

	public void totalCount(Model model) {
		Integer totalCount = adminDao.totalUserCount();
		model.addAttribute("TotalUser_count", totalCount);
	}

	public void epassApproveCount(Model model) {
		Integer approveCount = adminDao.approvedCount();
		model.addAttribute("TotalApprove_count", approveCount);
	}

	public void epassRejectCount(Model model) {
		Integer rejectCount = adminDao.rejectedCount();
		model.addAttribute("TotalReject_count", rejectCount);
	}

	public void travelPassApproveCount(Model model) {
		Integer travelApproveCount = adminDao.approveCount();
		model.addAttribute("TotalApprove_count1", travelApproveCount);

	}

	public void travelRejectCount(Model model) {
		Integer travelRejectCount = adminDao.rejectCount();
		model.addAttribute("TotalReject_count1", travelRejectCount);
	}

	public void epassPendingCount(Model model) {
		Integer epassPendingCount = adminDao.pendingEpassCount();
		model.addAttribute("TotalPending_count", epassPendingCount);
	}

	public void travelPendingCount(Model model) {
		Integer travelPendingCount = adminDao.pendingCount();
		model.addAttribute("TotalPending_count1", travelPendingCount);
	}
}
