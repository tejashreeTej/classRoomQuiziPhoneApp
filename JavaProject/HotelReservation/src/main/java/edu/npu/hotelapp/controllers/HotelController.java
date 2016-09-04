package edu.npu.hotelapp.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;






import edu.npu.hotelapp.domain.Customer;
import edu.npu.hotelapp.domain.Hotel;
import edu.npu.hotelapp.exceptions.HotelNotFoundException;
import edu.npu.hotelapp.services.HotelService;

@Controller
public class HotelController {
	@Autowired
	HotelService hotelservice;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	
	@RequestMapping(value = "/enterCityNameForm", method = RequestMethod.GET)
	public ModelAndView cityHotelForm() {
		ModelAndView modelView;
		modelView = new ModelAndView("enterCityNameForm");
		modelView.addObject("message", "");
		return modelView;
	}
	
	@RequestMapping(value = "/listHotels", method = RequestMethod.GET)
	public ModelAndView listStudentsInCourse(String cityName) throws HotelNotFoundException {

		List<Hotel> hotelList;
		ModelAndView modelView;
		
		if(cityName==null || cityName.isEmpty())
		{
			System.out.println("city null");
		}
		
		hotelList = hotelservice.findHotel(null, cityName);
		if(hotelList.size()<=0)
		{
			modelView = new ModelAndView("enterCityNameForm");
			modelView.addObject("message", "No Hotels Available");
		}
		else
		{
			modelView = new ModelAndView("listHotels");
			modelView.addObject("cityName", cityName);
			modelView.addObject("hotelList", hotelList);
		}
		
		return modelView;
	}
	
	//@RequestMapping(value = "/makeReservationForm", method = RequestMethod.GET)
	public ModelAndView makeHotelResrvation(@Valid Customer customer, BindingResult result, HttpSession session) throws HotelNotFoundException {
	
		ModelAndView modelView;
		String hotelName ="";
		
		//hotelList = hotelservice.
		modelView = new ModelAndView("listHotels");
		modelView.addObject("HotelName", hotelName);
		
		return modelView;
	}
	// Present the student data form
		//@RequestMapping(value = "/makeReservationForm", method = RequestMethod.GET)
		public ModelAndView newReservationForm() {
			ModelAndView modelView;
			
	 		modelView = new ModelAndView("makeReservationForm");
	 		modelView.addObject("hotel", new Hotel());
			return modelView;
		}
		
	//@RequestMapping(value = "/listhotels", method = RequestMethod.GET)
	public ModelAndView listHotelsInCity(String cityName) throws HotelNotFoundException {
		List<Hotel> hotelList;
		ModelAndView modelView;
		
		
		hotelList=hotelservice.findHotel(null, cityName);
	
		modelView = new ModelAndView("viewHotelList");
		modelView.addObject("hotelList", hotelList);
		
		return modelView;
	}
}
