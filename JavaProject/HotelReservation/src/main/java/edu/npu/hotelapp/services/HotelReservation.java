package edu.npu.hotelapp.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.npu.hotelapp.dao.CustomerDAO;
import edu.npu.hotelapp.domain.Customer;
import edu.npu.hotelapp.domain.Hotel;
import edu.npu.hotelapp.domain.Rooms;


@Transactional
@Service("hotelreservation")
public class HotelReservation {
	@Autowired
	@Qualifier("billingservice")
	BillingService billingservice;
	TaxService taxservice;
	HotelService hotelService;
	@Autowired
	@Qualifier("custdao")
	CustomerDAO custDAO;
	
	@Transactional(rollbackFor={java.io.IOException.class,edu.npu.hotelapp.exceptions.DuplicateResourceException.class})
	public void makereservation(Customer cust)
	{
		//assignRoomtoCustomer(cust,room)
		//System.out.println("makereservation");
		double totalcost=billingservice.CalculateBill(cust);
		//System.out.println("totalcost = "+totalcost);
		cust.setTotalCost(totalcost);
		//System.out.println("updateMyReservation ha ha custId ="+cust.getCustomerId());
		custDAO.insertCustomer(cust);
		
		
		
	}
	public void calculateTotalCost()
	{
		
	}
	public void assignRoomtoCustomer(Customer cust,Rooms room)
	{
		cust.setRoomId(room.getRoomId());
		cust.setHotelId(room.getHotelId());
		
	}
	public HotelReservation() {
		//System.out.println("HotelReservation get created ");
	}
	public void processCustomer(Customer cust)
	{
		//custDAO.insertCustomer(cust);
		
	}
	
	public List<Customer> getReservationsInHotel(int hotelId)
	{
		List<Customer> custList;
		custList=custDAO.getAllCustomers(hotelId);
		return  custList;
	}
	public Customer getReservation(int custId)
	{
		Customer customer;
	
		customer=custDAO.getCustomer(custId);
		return  customer;
	}
	
	public List<Customer> getReservationList()
	{
		List<Customer> custList;
		custList=custDAO.getAllCustomersList();
		return  custList;
	}
	
	public void addNewReservation(Customer cust)
	{
		custDAO.insertCustomer(cust);;
		
	}
	@Transactional
	public void updateReservation(Customer cust)
	{
		int oldId,newId;
		int custId=cust.getCustomerId();
		oldId=custId;
		custDAO.removeCustomer(custId);
		custDAO.insertCustomer(cust);
		newId=cust.getCustomerId();
		custDAO.updateCustomerId(oldId, newId);
		
	}
	public void deleteReservation(int custId)
	{
		
	custDAO.removeCustomer(custId);
		
	}
	@Transactional(rollbackFor={java.io.IOException.class})
	public void updateMyReservation(Customer cust)
	{
		int oldId,newId;
		int custId=cust.getCustomerId();
		oldId=custId;
		deleteReservation(custId);
		makereservation(cust);
		newId=cust.getCustomerId();
		//System.out.println("getReservation  oldId = "+oldId+"\t newId = "+newId);
		custDAO.updateCustomerId(oldId, newId);
	}
}
