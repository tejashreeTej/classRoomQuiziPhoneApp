package edu.npu.hotelapp.services;

import org.springframework.stereotype.Service;



@Service("taxservice")
public class TaxServiceStateTaxImpl implements TaxService{
double stateTax=0.08;
	public TaxServiceStateTaxImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculateTax(double amount) {
		// TODO Auto-generated method stub
		//System.out.println("calculateTax");
		
		double tax;
		tax=amount*stateTax;
		return tax;
	}
	}
