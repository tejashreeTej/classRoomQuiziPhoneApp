package edu.npu.hotelapp.dao;

import java.util.List;

import edu.npu.hotelapp.domain.Hotel;
import edu.npu.hotelapp.domain.Rooms;

public interface RoomsDAO {
	public void insertRooms(Rooms room);//insert Rooms
	
	public int getRoomsCount();
	public Rooms getRooms(int roomId);
	public List<Rooms> getAllRoomsOfHotel(int HotelID);//Retrieve Rooms
	public int getTotalRoomsofHotel(Hotel hotel,Rooms room);
	public int getAvailableRoomsofHotel(int hotelId,Rooms room);
	public int getRoomCost(Rooms room);
	
	public int deleteRooms(int hotelid,int roomid);///delete Rooms
	
	//public void insertRoomsInHotel(String hotelName);
	
	public int decrementAvailableRooms(Rooms room,int val);//update
	public int incremetAvailableRooms(Rooms room,int val);//update

	
		
}
