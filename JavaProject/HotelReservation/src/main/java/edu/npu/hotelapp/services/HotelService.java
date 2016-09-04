package edu.npu.hotelapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.npu.hotelapp.dao.HotelDAO;
//import org.springframework.transaction.annotation.Transactional;
import edu.npu.hotelapp.domain.Hotel;
import edu.npu.hotelapp.domain.Rooms;
import edu.npu.hotelapp.exceptions.HotelNotFoundException;

//import com.Hotel.Reservation.exception.HotelNotFoundException;


//@Transactional
@Service("hotelservice")
public class HotelService {
	@Autowired
	@Qualifier("hoteldao")
	private HotelDAO hotelDAO;
	
	public HotelService() {
		// TODO Auto-generated constructor stub
		//System.out.println("HotelService get created ");
	}
	public Hotel getHotel(int hotelId) 
	{
		 Hotel hotel;
		 hotel=hotelDAO.getHotel(hotelId);
		 
	    return hotel;
	}
	
	//@Transactional(rollbackFor=com.Hotel.Reservation.exception.HotelNotFoundException.class)
	public List<Hotel> findHotel(String hotelName,String cityName) throws HotelNotFoundException //throws HotelNotFoundException
	{
		List <Hotel> hotels;
		
		if(hotelName!=null && !hotelName.isEmpty() )
		{
			hotels=hotelDAO.findHotelByName(hotelName);
		}
		else if(cityName!=null && !cityName.isEmpty())
		{
			hotels=hotelDAO.findHotelAvailable(cityName, null, null);
		}
		else
		{
			hotels=hotelDAO.findAllHotels();
		}
		if (hotels == null) {
			//throw new HotelNotFoundException("pecified hotel not found ");
		}
		//printHotels(hotels);
		return hotels;
	}
	
	public static void  printHotels(List<Hotel> hotels)
	{
		System.out.println("Hotels :");
		if(hotels!=null)
		{
		for(Hotel hotel:hotels)
		{
			System.out.println(hotel);
		}
		}
	}
	//readOnly=false 
	//@Transactional(propagation=Propagation.NESTED)
	public void insertHotel(Hotel hotel)
	{
		hotelDAO.insertHotel(hotel);

	}
	//@Transactional(propagation=Propagation.REQUIRED)
	public void deleteHotel(int hotelId)
	{
		hotelDAO.deleteHotel(hotelId);
	}
	public void changeCityOfHotel(Hotel hotel,String city)
	{
		hotelDAO.changeCityOfHotel(hotel, city);
	}
	
	public String getHotelCity(int hotelId)
	{
		return hotelDAO.getHotelCity(hotelId);
	}
	
}
