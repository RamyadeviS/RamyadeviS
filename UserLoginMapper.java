package com.chainsys.epassproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.epassproject.model.UserDetails;

public class UserLoginMapper implements RowMapper<UserDetails> {
	@Override
	public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDetails register = new UserDetails();
        
		Long aadharNo = rs.getLong("aadhar_no");
		register.setAadharNo(aadharNo);

		
		String userName = rs.getString("user_name");
		register.setUserName(userName);

		String password = rs.getString("password");
		register.setPassword(password);
		
		Long mobileNo=rs.getLong("mobile_no");
		register.setMobileNo(mobileNo);
		return register;
	}
}