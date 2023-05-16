package com.chainsys.epassproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.epassproject.model.EpassForm;

public class EpassStatusMapper implements RowMapper<EpassForm> {

	@Override
	public EpassForm mapRow(ResultSet rs, int rowNum) throws SQLException {
		EpassForm epassView = new EpassForm();
		String status=rs.getString("status");
		epassView.setStatus(status);
		return epassView;
	}

}
