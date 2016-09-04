package edu.npu.hotelapp.services;

import edu.npu.hotelapp.domain.Customer;





public interface BillingService {
	public double CalculateBill(Customer cust);
}
