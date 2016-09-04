package edu.npu.hotelapp.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.npu.hotelapp.domain.Rooms;


@ContextConfiguration("classpath:service.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RoomsDAOTest {

	@Autowired
	@Qualifier("roomdao")
	private RoomsDAO roomsDAO;

	@Test
	public void testRoomInsert() {
		int HotelId = 1;// use hotel Id for test It should be present in
						// Database
		int origRoomCount, newRoomCount;
		Rooms room1, room2;

		room1 = new Rooms("Delux", 45, 20, 88);
		room2 = new Rooms("Deluxnew", 40, 20, 90);

		origRoomCount = roomsDAO.getRoomsCount();

		room1.setHotelId(HotelId);
		roomsDAO.insertRooms(room1);

		Rooms getroom1 = roomsDAO.getRooms(room1.getRoomId());

		room2.setHotelId(HotelId);
		roomsDAO.insertRooms(room2);

		newRoomCount = roomsDAO.getRoomsCount();
		System.out.println("testRoomInsert 8");
		assertTrue((origRoomCount + 2) == newRoomCount);
	}

	 @Test
	public void testDecrementAvailableRooms() {
		int origAvailableRooms, newAvailableRooms;

		Rooms room1 = roomsDAO.getRooms(1);
		origAvailableRooms = roomsDAO.getAvailableRoomsofHotel(
				room1.getHotelId(), room1);
		roomsDAO.decrementAvailableRooms(room1, 5);
		newAvailableRooms = roomsDAO.getAvailableRoomsofHotel(
				room1.getHotelId(), room1);
		assertTrue((origAvailableRooms - 5) == newAvailableRooms);
	}

	 @Test
	public void testIncrementAvailableRooms() {
		int origAvailableRooms, newAvailableRooms;

		Rooms room1 = roomsDAO.getRooms(1);
		origAvailableRooms = roomsDAO.getAvailableRoomsofHotel(
				room1.getHotelId(), room1);
		roomsDAO.incremetAvailableRooms(room1, 2);
		newAvailableRooms = roomsDAO.getAvailableRoomsofHotel(
				room1.getHotelId(), room1);
		assertTrue((origAvailableRooms + 2) == newAvailableRooms);

	}
}
