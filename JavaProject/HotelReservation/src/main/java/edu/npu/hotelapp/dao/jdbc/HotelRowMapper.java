package edu.npu.hotelapp.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.hotelapp.domain.Hotel;


public class HotelRowMapper implements RowMapper<Hotel> {

	public Hotel mapRow(ResultSet resultSet, int row) throws SQLException {
		
		Hotel hotel;
		
		hotel = new Hotel();
		
		hotel.setHotelId(resultSet.getInt("HotelId"));
		hotel.setHotelName(resultSet.getString("HotelName"));
		hotel.setHotelCity(resultSet.getString("Hotel_city"));
		hotel.setHotelAvailability(resultSet.getInt("HotelAvailable"));
		return hotel;
	}
	public HotelRowMapper() {
		// TODO Auto-generated constructor stub
	}

}
