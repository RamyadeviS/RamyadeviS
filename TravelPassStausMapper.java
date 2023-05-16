package com.chainsys.epassproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.epassproject.model.TravelEpassForm;

public class TravelPassStausMapper implements RowMapper<TravelEpassForm> {

	@Override
	public TravelEpassForm mapRow(ResultSet rs, int rowNum) throws SQLException {
		TravelEpassForm travelPassView = new TravelEpassForm();
		String status=rs.getString("status");
		travelPassView.setAction(status);
		return travelPassView;
	}

}
 