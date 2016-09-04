package edu.npu.hotelapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.npu.hotelapp.domain.Hotel;
import edu.npu.hotelapp.domain.Rooms;
import edu.npu.hotelapp.exceptions.HotelNotFoundException;
import edu.npu.hotelapp.services.RoomService;
@Controller
public class RoomController {
	@Autowired
	RoomService roomservice;
	private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
	
	@RequestMapping(value = "/viewRoomsList", method = RequestMethod.GET)
	public ModelAndView listRoomsInHotel(String buttonId,int []  hotelId)  {
int ibuttonId=0;
		
		char c=buttonId.charAt(buttonId.length()-1);

		ibuttonId=Integer.valueOf(c)-49;
		int custHotelId;
		custHotelId=hotelId[ibuttonId];
		
		List<Rooms> roomsList;
		
		ModelAndView modelView;
		roomsList=roomservice.findRoom(custHotelId);
		modelView = new ModelAndView("viewRoomsList");
		modelView.addObject("roomsList", roomsList);
		
		return modelView;
	}
	
	//@RequestMapping(value = "/listRooms", method = RequestMethod.GET)
	public ModelAndView listRoomsInHotels(int hotelId) {
		List<Rooms> roomsList;
		ModelAndView modelView;
		
		roomsList=roomservice.findRoom(hotelId);
		
		modelView = new ModelAndView("viewRoomList");
		modelView.addObject("roomsList", roomsList);
		
		return modelView;
	}
	//@RequestMapping(value = "/makeReservation", method = RequestMethod.POST)
	public ModelAndView makeReservation(int roomId) {
		Rooms room;
		ModelAndView modelView;
		
		room=roomservice.getRoom(roomId);
		
		modelView = new ModelAndView("viewReservation");
		modelView.addObject("Room", room);
		
		return modelView;
	}
}
