package edu.npu.hotelapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.npu.hotelapp.dao.RoomsDAO;
import edu.npu.hotelapp.domain.Rooms;


@Service("roomservice")
public class RoomService {
	@Autowired
	@Qualifier("roomdao")
	private RoomsDAO roomDAO;
	
	public RoomService() {
		// TODO Auto-generated constructor stub
		//System.out.println("HotelService get created ");
	}
	
	public List<Rooms> findRoom(int hotelId) 
	{
		 List<Rooms> rooms;
		 rooms=roomDAO.getAllRoomsOfHotel(hotelId);
	    	return rooms;
	}
	public Rooms getRoom(int roomId) 
	{
		Rooms rooms;
		 rooms=roomDAO.getRooms(roomId);
	    return rooms;
	}
	public int getRoomCost(int roomId) 
	{
		 Rooms room=new Rooms();
		 room.setRoomId(roomId);
		 int cost;
		 cost=roomDAO.getRoomCost(room);
	    return cost;
	}
}
