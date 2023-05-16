package com.chainsys.epassproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.epassproject.model.UserDetails;

public class NameMapper implements RowMapper<UserDetails> {

	@Override
	public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDetails register = new UserDetails();
		rs.getString("user_name");
		register.getUserName();
		return register;
	}

}