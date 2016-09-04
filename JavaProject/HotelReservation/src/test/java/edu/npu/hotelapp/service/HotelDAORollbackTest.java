package edu.npu.hotelapp.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.logging.impl.Log4JLogger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.npu.hotelapp.dao.HotelDAO;
import edu.npu.hotelapp.dao.RoomsDAO;
import edu.npu.hotelapp.domain.Hotel;
import edu.npu.hotelapp.domain.Rooms;
import edu.npu.hotelapp.exceptions.HotelNotFoundException;


@ContextConfiguration("classpath:service.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelDAORollbackTest {
	@Autowired
	@Qualifier("hoteldao")
	private HotelDAO hotelDAO;
	
	@Autowired
	@Qualifier("roomdao")
	private RoomsDAO roomsDAO;
	
  //private Logger logger =  Logger.getLogger(HotelDAORollbackTest.class);
	
	@Test(expected=HotelNotFoundException.class)
	public void testFindHotel() throws HotelNotFoundException {
		hotelDAO.findHotelByName("ABC");
	}
	
	 @Test
	public void testInsertHotel()
	{
		Hotel hotel=new Hotel("HotelNew","Fremont");
		
		List <Rooms> roomlist=new ArrayList<Rooms>();
		
		Rooms room1=new Rooms("King",40,20,68);
		Rooms room2=new Rooms("Queen",30,10,55);
		
		room1.setHotelId(1);
		room2.setHotelId(1);
		roomlist.add(room1);
		roomlist.add(room2);
		
		hotel.setRooms(roomlist);
		
		try
		{
		hotelDAO.insertHotel(hotel);
		}
		catch(Exception ex)
		{
			//List<Rooms> getroomList=roomsDAO.getAllRoomsOfHotel(hotel);
			//assertNull(getroomList);	
			System.out.println("Exception Ocurred : "+ex.getMessage());
		}
		//List<Rooms> getroomList=roomsDAO.getAllRoomsOfHotel(hotel);
		//assertEquals(getroomList.size(),2);
	}
	
	
	@Test
	public void testDeleteHotel()
	{
		int hotelID=1;
		Hotel hotel=new Hotel("testName","testCity");
		hotel.setHotelId(hotelID);
	
		try
		{
		hotelDAO.deleteHotel(hotelID);
		}
		catch(Exception ex)
		{
			//List<Rooms> getroomList=roomsDAO.getAllRoomsOfHotel(hotel);
			//assertNotNull(getroomList);	
			Hotel getHotel=hotelDAO.getHotel(hotelID);
			assertNotNull(getHotel);
		}
		Hotel getHotel=hotelDAO.getHotel(hotelID);
		//System.out.println(getHotel);
		assertNull(getHotel);
		//List<Rooms> getroomList=roomsDAO.getAllRoomsOfHotel(hotel);		
		//System.out.println("getroomList.isEmpty()"+getroomList.isEmpty());
		//assertTrue(getroomList.isEmpty());
	}
}




