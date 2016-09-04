package edu.npu.hotelapp.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.hotelapp.domain.Rooms;


public class RoomsRowMapper implements RowMapper<Rooms>  {

public Rooms mapRow(ResultSet resultSet, int row) throws SQLException {
		
	Rooms rooms;
		
	rooms = new Rooms();
	
	rooms.setRoomId(resultSet.getInt("RoomId"));
	rooms.setRoomType(resultSet.getString("RoomType"));
	rooms.setTotalRoom(resultSet.getInt("TotalRoom"));
	rooms.setAvailableRooms(resultSet.getInt("AvailableRooms"));
	rooms.setRoomCost(resultSet.getInt("RoomCost"));
	rooms.setHotelId(resultSet.getInt("HotelId"));
	return rooms;
	}
	public RoomsRowMapper() {
		// TODO Auto-generated constructor stub
	}

}
