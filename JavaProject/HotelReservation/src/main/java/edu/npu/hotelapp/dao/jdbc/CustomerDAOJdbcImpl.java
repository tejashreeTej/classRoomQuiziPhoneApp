package edu.npu.hotelapp.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import edu.npu.hotelapp.dao.CustomerDAO;
import edu.npu.hotelapp.dao.RoomsDAO;
import edu.npu.hotelapp.domain.Customer;
import edu.npu.hotelapp.domain.Hotel;
import edu.npu.hotelapp.exceptions.DuplicateResourceException;
import edu.npu.hotelapp.exceptions.UnknownResourceException;

@Repository("custdao")
public class CustomerDAOJdbcImpl implements CustomerDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private CustomerRowMapper customerRowMapper;

	public CustomerDAOJdbcImpl() {
		//System.out.println("CustomerDAOJdbcImpl get created ");
	}

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		customerRowMapper = new CustomerRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("Customer")
		                 .usingGeneratedKeyColumns("CustId");
		                 //.usingColumns("Cust_First_Name", "Cust_Last_Name","HotelId", "RoomId","TotalCost","night_num");
	}
	
	@Override
	public void insertCustomer(Customer cust) {
		// TODO Auto-generated method stub
SqlParameterSource params = new BeanPropertySqlParameterSource(cust);
Number newId;
//System.out.println("\n***** insertCustomer*****\nfirstName = "+ cust.getCustFirstName()+"\nlastName = "+cust.getCustLastName()+"\nihotelId = "+cust.getHotelId()+"\niroomId = "+cust.getRoomId()+"\nnightNo ="+cust.getNoOfNights());
try{

	     newId = jdbcInsert.executeAndReturnKey(params);
}
catch(Exception ex)
{
	throw new DuplicateResourceException("Duplicate resources "+ex.getMessage());
}
	    cust.setCustomerId(newId.intValue());
	}

	@Override
	public List<Customer> getAllCustomers(int hotelId) {
	
		String sql = "SELECT * FROM Customer where HotelId=?";
		List<Customer> customerList=null;
		
		customerList = jdbcTemplate.query(sql, 
				customerRowMapper,hotelId);
				return customerList;
	}

	@Override
	public Customer findCustomer(int customerId, String customerFirstName,
			String customerlastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCustomerOfHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeCustomer(int customerId) {
		MapSqlParameterSource params;
		int rowsAffected;
		String sql = "delete from Customer where Customer.CustId=:id";
		
		params = new MapSqlParameterSource("id", customerId);
		
		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;	
		}

	@Override
	public Customer getCustomer(int custId) {
		//System.out.println("getCustomer  custId = "+custId);
		
		String sql = "SELECT * FROM Customer WHERE CustId=?";
		Customer cust=null;
		try
		{
				cust = jdbcTemplate.queryForObject(sql,
						customerRowMapper,custId);
				//System.out.println("getCustomer  cust = "+cust);
				
		}
		catch(Exception ex)
		{
			throw new UnknownResourceException("Invalid Reservation");
		}
				return cust;
	}

	@Override
	public List<Customer> getAllCustomersList() {
		String sql = "SELECT * FROM Customer ";
		List<Customer> customerList=null;
		
		customerList = jdbcTemplate.query(sql, 
				customerRowMapper);
				return customerList;
	}

	@Override
	public void addCustomer(Customer cust) {
	int customerId=cust.getCustomerId();
	String custFirstName=cust.getCustFirstName();
		String custLastName=cust.getCustLastName();
		double totalCost=cust.getTotalCost();
		int HotelId=cust.getHotelId();
		 int RoomId=cust.getRoomId();
		int noOfNights=cust.getNoOfNights();
		String sql = "insert into Customer values(customerId,custFirstName,custLastName,totalCost,HotelId,RoomId,noOfNights;)";
		int curTotalOrders, newTotalOrders;
		String prodName;
		MapSqlParameterSource params;
		int rowsAffected;
		//int hotelId = hotel.getHotelId();
		
		//params = new MapSqlParameterSource("id", hotelId);
		//params.addValue("newCity", newCity);
		//rowsAffected = dbTemplate.update(sql, params);
		//return rowsAffected;
		
	}

	@Override
	public int updateCustomerId(int oldId, int newId) {
		String sql = "update Customer set CustId=:oldId where CustId=:newId";
		
		MapSqlParameterSource params;
		int rowsAffected;
		
		params = new MapSqlParameterSource("newId", newId);
		params.addValue("oldId", oldId);
		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;
	
	}

}
