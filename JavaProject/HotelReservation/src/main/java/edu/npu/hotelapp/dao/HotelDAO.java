package edu.npu.hotelapp.dao;

import java.util.Date;
import java.util.List;

import edu.npu.hotelapp.domain.Hotel;
import edu.npu.hotelapp.exceptions.HotelNotFoundException;


//import com.Hotel.Reservation.exception.HotelNotFoundException;

public interface HotelDAO {
	public int getTotalHotelCount();//Retrieve Hotel
	public Hotel getHotel(int hotelId);	
	public String getHotelCity(int hotelId);	
	
	public List<Hotel> findHotelAvailable(String city,Date toDate,Date fromDate);
	public List<Hotel> findHotelByName(String hotelName) throws HotelNotFoundException;
	public List<Hotel> findAllHotels();
	
	public void insertHotel(Hotel hotel);//insert Hotel
	
	public int deleteHotel(int hotelID);//delete Hotel
	
	public int changeAvailabilityOfHotel(Hotel hotel,int isAvailable);//update AvailabilityColumn
	public int changeCityOfHotel(Hotel hotel,String newCity);
}
