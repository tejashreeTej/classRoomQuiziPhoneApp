package edu.npu.hotelapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.npu.hotelapp.domain.Customer;


@Service("billingservice")
public class BillingServiceImpl implements BillingService {
	@Autowired
	private TaxService  taxService;
	@Autowired
	private RoomService roomService;
	
	public BillingServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double CalculateBill(Customer cust) {
		// TODO Auto-generated method stub
		//System.out.println("CalculateBill");
		int roomCost, night_num;
		night_num=cust.getNoOfNights();
		/*if(roomService==null)
		{
			System.out.println("roomService is null");
			
		}
		else
		{
			System.out.println("roomService is NOT null");
		}
		*/
		roomCost=roomService.getRoomCost(cust.getRoomId());
		double tax ,amount,totalcost;
		amount=night_num * roomCost;
		if(taxService==null)
		{
			System.out.println("taxService is null");
			
		}
		else
		{
			System.out.println("taxService is NOT null");
		}
		tax=taxService.calculateTax(amount);
		totalcost=amount + tax;
		return totalcost;
	}


}
