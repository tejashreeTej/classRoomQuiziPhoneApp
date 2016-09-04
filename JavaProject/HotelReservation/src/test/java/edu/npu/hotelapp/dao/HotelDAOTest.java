package edu.npu.hotelapp.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.npu.hotelapp.domain.Hotel;



@ContextConfiguration("classpath:service.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelDAOTest {

	@Autowired
@Qualifier("hoteldao")
	private HotelDAO hotelDAO;
	
	@Test
	public void testgetTotalHotel() {
		int toatalHotels;
		toatalHotels=hotelDAO.getTotalHotelCount();
		List<Hotel> hotelList=hotelDAO.findAllHotels();
        assertEquals(toatalHotels,hotelList.size());
        
	}
	@Test
	public void testInsertHotel()
	{
		int origHotelCount,newHotelCount;
		
		origHotelCount=hotelDAO.getTotalHotelCount();
		Hotel newHotel=new Hotel("Holiday Inn1","Fremont");
		hotelDAO.insertHotel(newHotel);
		Hotel getnewHotel=hotelDAO.getHotel(newHotel.getHotelId());
		
		newHotelCount=hotelDAO.getTotalHotelCount();
		
		assertEquals((origHotelCount+1),newHotelCount);
					
	}
	@Test
	public void testChangeAvailabilityOfHotel()
	{
		int HotelId = 1;// use hotel Id for test It should be present in Database
		int origavailability,newavailability;
		Hotel hotel1=hotelDAO.getHotel(HotelId);
		origavailability=hotel1.getHotelAvailability();
		
		if(origavailability!=0)
		hotelDAO.changeAvailabilityOfHotel(hotel1, 0);
		else
			hotelDAO.changeAvailabilityOfHotel(hotel1, 1);
		
	newavailability=hotelDAO.getHotel(HotelId).getHotelAvailability();
	assertNotEquals(origavailability,newavailability);
	}

}
