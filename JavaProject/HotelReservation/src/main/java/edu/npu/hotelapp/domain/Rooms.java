package edu.npu.hotelapp.domain;

public class Rooms {
private int roomId;
private String roomType;
private int totalRoom;
private int availableRooms;
private double roomCost;
private int hotelId;


	public Rooms() {
		// TODO Auto-generated constructor stub
	}


	public Rooms(String roomType, int totalRoom, int availableRoom,
			double roomCost) {
		super();
		this.roomType = roomType;
		this.totalRoom = totalRoom;
		this.availableRooms = availableRoom;
		this.roomCost = roomCost;
	}


	public int getRoomId() {
		return roomId;
	}


	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public int getTotalRoom() {
		return totalRoom;
	}


	public void setTotalRoom(int totalRoom) {
		this.totalRoom = totalRoom;
	}


	public int getAvailableRooms() {
		return availableRooms;
	}


	public void setAvailableRooms(int availableRoom) {
		this.availableRooms = availableRoom;
	}


	public double getRoomCost() {
		return roomCost;
	}


	public void setRoomCost(double roomCost) {
		this.roomCost = roomCost;
	}


	public int getHotelId() {
		return hotelId;
	}


	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}


	@Override
	public String toString() {
		return "Rooms [roomId=" + roomId + ", roomType=" + roomType
				+ ", totalRoom=" + totalRoom + ", availableRooms="
				+ availableRooms + ", roomCost=" + roomCost + ", hotelId="
				+ hotelId + "]";
	}
	

}
