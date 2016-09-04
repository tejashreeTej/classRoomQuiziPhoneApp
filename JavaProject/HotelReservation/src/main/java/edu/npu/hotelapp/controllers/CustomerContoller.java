package edu.npu.hotelapp.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.npu.hotelapp.domain.Customer;
import edu.npu.hotelapp.domain.Hotel;
import edu.npu.hotelapp.domain.Rooms;
import edu.npu.hotelapp.exceptions.DuplicateResourceException;
import edu.npu.hotelapp.exceptions.HotelNotFoundException;
import edu.npu.hotelapp.exceptions.UnknownResourceException;
import edu.npu.hotelapp.services.HotelReservation;
import edu.npu.hotelapp.services.HotelService;
import edu.npu.hotelapp.services.RoomService;


@Controller
public class CustomerContoller {
	@Autowired
	HotelReservation hotelreservation;
	@Autowired
	HotelService hotelservice;
	@Autowired
	RoomService roomservice;

private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
	
	@RequestMapping(value = "/makeReservationForm", method = RequestMethod.GET)
	public ModelAndView showMakeResevation(String buttonId,int[] hotelId,int [] roomId)  {
		
		
	
		int ibuttonId=0;
		
		char c=buttonId.charAt(buttonId.length()-1);

		ibuttonId=Integer.valueOf(c)-49;
		int custHotelId,custRoomId;
		Hotel hotel;
		Rooms room;
		ModelAndView modelView;
		
		custHotelId=hotelId[ibuttonId];
		custRoomId=roomId[ibuttonId];
		
		hotel=hotelservice.getHotel(custHotelId);
		
		room=roomservice.getRoom(custRoomId);
		
		modelView = new ModelAndView("makeReservationForm");
		modelView.addObject("hotel", hotel);
		modelView.addObject("room", room);
		return modelView;
	}
	
	@RequestMapping(value = "/makemyReservationForm", method = RequestMethod.GET)
	public ModelAndView processReservation(String hotelId,String roomId,String firstName,String lastName,int nightNo,String hotelCity) //,Date toDate,Date fromDate
	{
	
		
		int ihotelId=Integer.valueOf(hotelId);
		int iroomId=Integer.valueOf(roomId);
		ModelAndView modelView;
		//Customer cust=new Customer("Tejaswini","Jagtap");
		
		Customer cust=new Customer(firstName,lastName,ihotelId,iroomId,nightNo);
		//cust.setFromDate(fromDate);
		//cust.setToDate(toDate);
		try
		{
		hotelreservation.makereservation(cust);
		}
		catch(Exception ex)
		{
			throw new DuplicateResourceException(ex.getMessage());
		}
		
		modelView = new ModelAndView("reservationSuccess");
		modelView.addObject("customer",cust);
		return modelView;
	}
	
	@RequestMapping(value = "/enterReservationIdForm", method = RequestMethod.GET)
	public ModelAndView cityHotelForm() {
		ModelAndView modelView;
		modelView = new ModelAndView("enterReservationIdForm");
		modelView.addObject("message", "");
		return modelView;
	}
	
	
	
	@RequestMapping(value = "/checkreservationForm", method = RequestMethod.GET)
	public ModelAndView listReservation(int  reservationId)  {
		logger.info(" listReservation");
		Customer customer=null;
		Hotel hotel=null;
		Rooms room=null;
		ModelAndView modelView;
		
		try{
		customer=hotelreservation.getReservation(reservationId);
		hotel=hotelservice.getHotel(customer.getHotelId());
		room=roomservice.getRoom(customer.getRoomId());
		}
		catch(UnknownResourceException ex)
		{
			modelView = new ModelAndView("enterReservationIdForm");
			modelView.addObject("message", "No Reservation with Id"+reservationId);
		
		}
		catch(Exception ex)
		{
			throw new UnknownResourceException("Resource Not found "+ex.getMessage());
		}
		if(customer==null || hotel==null || room==null)
		{
			modelView = new ModelAndView("enterReservationIdForm");
			modelView.addObject("message", "No Such Reservation Id");
			
		}
		else
		{
			modelView = new ModelAndView("checkreservationForm");
			modelView.addObject("customer", customer);
			modelView.addObject("hotel", hotel);
			modelView.addObject("room", room);
		}
		
		return modelView;
	}
	
	@RequestMapping(value = "/updateReservationForm", method = RequestMethod.GET)
	public ModelAndView showupdateReservationForm() {
		ModelAndView modelView;
		modelView = new ModelAndView("updateReservationForm");
		modelView.addObject("message", "");
		return modelView;
	}
	@RequestMapping(value = "/updationForm", method = RequestMethod.GET)
	public ModelAndView updateReservation(int custId) {
		Customer customer=null;
		Hotel hotel=null;
		Rooms room=null;
		ModelAndView modelView;
		
		try{
		customer=hotelreservation.getReservation(custId);
		customer.setCustomerId(custId);
		hotel=hotelservice.getHotel(customer.getHotelId());
		room=roomservice.getRoom(customer.getRoomId());
		}
		catch(UnknownResourceException ex)
		{
			modelView = new ModelAndView("updateReservationForm");
			modelView.addObject("message", "No Reservation with Id"+custId);
		
		}
		catch(Exception ex)
		{
			throw new UnknownResourceException("Resource Not found "+ex.getMessage());
		}
		if(customer==null || hotel==null || room==null)
		{
			modelView = new ModelAndView("updateReservationForm");
			modelView.addObject("message", "No Such Reservation Id");
			
		}
		else
		{
			modelView = new ModelAndView("updationForm");
			modelView.addObject("customer", customer);
			modelView.addObject("hotel", hotel);
			modelView.addObject("room", room);
		}
		
		return modelView;
	
	}
	
	@RequestMapping(value = "/successForm", method = RequestMethod.GET)
	public ModelAndView processUpdateReservation(String hotelId,String roomId,int customerId,String firstName,String lastName,int nightNo)  {
		logger.info(" processUpdateReservation");
		//String hotelId,String roomId,int customerId,String firstName,String lastName,int nightNo
		//int hotelId=1;
		//int roomId=2;int customerId =1;String firstName="RamDas";
		//String lastName="dham";
		//int nightNo=2;
		//System.out.println("\ncustomerId ="+customerId+"\nhotelId"+hotelId+"\nroomId"+ roomId+"\nfirstName"+ firstName+"\nlastName"+lastName+"\nnightNo"+nightNo);
		int ihotelId=Integer.valueOf(hotelId);
		int iroomId=Integer.valueOf(roomId);
		int icustomerId=Integer.valueOf(customerId);
		ModelAndView modelView;
		//Customer cust=new Customer("Tejaswini","Jagtap");
		
		Customer cust=new Customer(firstName,lastName,ihotelId,iroomId,nightNo);
		cust.setCustomerId(icustomerId);
		try
		{
			
			int custId=icustomerId;
			Customer customer=new Customer();
			//Customer customer=hotelreservation.getReservation(custId);
			System.out.println("Customer");
			if(customer==null)
			{
				modelView = new ModelAndView("deleteReservationForm");
				modelView.addObject("message", "Invalid reservation Id");
				return modelView;
				
			}
			System.out.println("updateMyReservation");
		hotelreservation.updateMyReservation(cust);
		}
		catch(Exception ex)
		{
			throw new DuplicateResourceException(ex.getMessage());
		}
		
		modelView = new ModelAndView("successForm");
		modelView.addObject("message","Updation is Successfull.");
		return modelView;
	}
	
	@RequestMapping(value = "/deleteReservationForm", method = RequestMethod.GET)
	public ModelAndView showDeleteReservationForm() {
		ModelAndView modelView;
		modelView = new ModelAndView("deleteReservationForm");
		modelView.addObject("message", "");
		return modelView;
	}
	
	@RequestMapping(value = "/successForm", method = RequestMethod.POST)
	public ModelAndView deleteReservation(int custId) {
		ModelAndView modelView;
		try
		{
			
			Customer cust=hotelreservation.getReservation(custId);
			if(cust==null)
			{
				modelView = new ModelAndView("deleteReservationForm");
				modelView.addObject("message", "Invalid reservation Id");
				return modelView;
				
			}
		hotelreservation.deleteReservation(custId);;
		}
		catch(Exception ex)
		{
			throw new DuplicateResourceException(ex.getMessage());
		}
		modelView = new ModelAndView("successForm");
		modelView.addObject("message","Deletion is Successfull.");
		return modelView;
	}
}
