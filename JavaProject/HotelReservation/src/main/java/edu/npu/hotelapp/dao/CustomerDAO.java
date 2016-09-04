package edu.npu.hotelapp.dao;

import java.util.Date;
import java.util.List;

import edu.npu.hotelapp.domain.Customer;
import edu.npu.hotelapp.domain.Hotel;


public interface CustomerDAO {
	public void insertCustomer(Customer cust);
	public void addCustomer(Customer cust);
	public int getTotalCustomerOfHotel(Hotel hotel);
	public Customer getCustomer(int custId);

	public List<Customer> getAllCustomers(int hotelId);
	public List<Customer> getAllCustomersList();
	public Customer findCustomer(int customerId, String customerFirstName,
			String customerlastName);
	public int updateCustomerId(int oldId,int newId);
	public int removeCustomer(int customerId);

}
