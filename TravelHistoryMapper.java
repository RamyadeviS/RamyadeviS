package com.chainsys.epassproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.epassproject.model.EpassForm;

public class TravelHistoryMapper  implements RowMapper {

	@Override
	public 	EpassForm mapRow(ResultSet rs, int rowNum) throws SQLException {
		EpassForm epass = new EpassForm();

		Long applicationNo = rs.getLong("application_no");
		epass.setApplicationNo(applicationNo);
		
		return epass;
	}

}