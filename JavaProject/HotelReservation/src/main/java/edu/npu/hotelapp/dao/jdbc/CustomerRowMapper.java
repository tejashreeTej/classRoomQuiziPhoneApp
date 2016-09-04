package edu.npu.hotelapp.dao.jdbc;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.tree.TreePath;

import org.springframework.jdbc.core.RowMapper;

import edu.npu.hotelapp.domain.Customer;


public class CustomerRowMapper implements RowMapper<Customer>   {

	public CustomerRowMapper() {
		// TODO Auto-generated constructor stub
	}
	public Customer mapRow(ResultSet resultSet, int row) throws SQLException {
		
		Customer customer;
		
		customer = new Customer();
		
		customer.setCustomerId(resultSet.getInt("CustId"));
		customer.setCustFirstName(resultSet.getString("Cust_First_Name"));
		customer.setCustLastName(resultSet.getString("Cust_Last_Name"));
		customer.setHotelId(resultSet.getInt("HotelId"));
		customer.setRoomId(resultSet.getInt("RoomId"));
		customer.setTotalCost(resultSet.getDouble("TotalCost"));
		customer.setNoOfNights(resultSet.getInt("noOfNights"));
		
		
		return customer;
	}

}
