package edu.npu.hotelapp.domain;

import java.util.List;


public class Hotel {
	private int hotelId;
	private String hotelName;
	private String hotelCity;
	private int hotelAvailability;
	private List<Rooms> rooms;

	public Hotel() {
		// TODO Auto-generated constructor stub
		
	}

	public Hotel(String hotelName, String hotelCity) {
		super();
		//this.hotelId=10;
		this.hotelName = hotelName;
		this.hotelCity = hotelCity;
		//rooms=(List<Rooms>) new Rooms();
		this.hotelAvailability=1;
	}


	


	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName
				+ ", hotelCity=" + hotelCity + ", hotelAvailability="
				+ hotelAvailability + ", ]";
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelCity() {
		return hotelCity;
	}

	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}

	public int getHotelAvailability() {
		return hotelAvailability;
	}

	public void setHotelAvailability(int hotelAvailability) {
		this.hotelAvailability = hotelAvailability;
	}

	public List<Rooms> getRooms() {
		return rooms;
	}

	public void setRooms(List<Rooms> rooms) {
		this.rooms = rooms;
	}
	

}
