package edu.npu.hotelapp.dao.jdbc;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jndi.JndiTemplate;

import edu.npu.hotelapp.dao.HotelDAO;
import edu.npu.hotelapp.dao.RoomsDAO;
import edu.npu.hotelapp.domain.Hotel;
import edu.npu.hotelapp.domain.Rooms;
import edu.npu.hotelapp.exceptions.HotelNotFoundException;


//import com.Hotel.Reservation.exception.HotelNotFoundException;

//@Transactional
@Repository("hoteldao")
public class HotelDAOJdbcImpl implements HotelDAO{
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("roomdao")
	RoomsDAO roomDAO;
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private HotelRowMapper hotelRowMapper;

	public HotelDAOJdbcImpl() {
		//System.out.println("HotelDAOJdbcImpl get created ");
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		dbTemplate = new NamedParameterJdbcTemplate(dataSource);
		hotelRowMapper = new HotelRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("Hotel")
		                 .usingGeneratedKeyColumns("Hotel_id")
		                 .usingColumns("HotelName", "Hotel_city", "HotelAvailable");
	}
	

	@Override
	public int getTotalHotelCount() {
		String sql = "select count(*) FROM Hotel";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	
	@Override
	public void insertHotel(Hotel hotel) {
		
		SqlParameterSource params = new BeanPropertySqlParameterSource(hotel);
		
		Number newId = jdbcInsert.executeAndReturnKey(params);
		
	    hotel.setHotelId(newId.intValue());
	    
	    List<Rooms> roomsofhotel=hotel.getRooms();
	   
	    if(roomsofhotel!=null)
	    {
	    for(Rooms room:roomsofhotel)
	    {
	    	room.setHotelId(hotel.getHotelId());
	    	roomDAO.insertRooms(room);
	    }
	    }
	    changeAvailabilityOfHotel(hotel,1);
	}

	@Override
	public List<Hotel> findHotelAvailable(String city, Date toDate,
			Date fromDate) {
		String sql = "SELECT * FROM Hotel WHERE Hotel_City=? And HotelAvailable=1";
		
		List<Hotel> hotelList=null;
		
				hotelList = jdbcTemplate.query(sql, 
		                 hotelRowMapper,city);
				return hotelList;
	}
@Override
	public List<Hotel> findHotelByName(String hotelName) throws HotelNotFoundException
	{
		
		String sql = "SELECT * FROM Hotel WHERE HotelName=? And HotelAvailable=1";
		
		List<Hotel> hotelList=null;
		
				hotelList = jdbcTemplate.query(sql, 
		                 hotelRowMapper,hotelName);
				
				if(hotelList==null || hotelList.size()==0 )
				{
					//throw new HotelNotFoundException("There No Hotel Named "+hotelName);
				}
				
				return hotelList;				
	}
	@Override
	public int changeAvailabilityOfHotel(Hotel hotel,int isAvailable) {
		String sql = "update Hotel set HotelAvailable=:HotelAvailable where HotelId=:id";
		int curTotalOrders, newTotalOrders;
		String prodName;
		MapSqlParameterSource params;
		int rowsAffected;
		int hotelId = hotel.getHotelId();
		
		params = new MapSqlParameterSource("id", hotelId);
		params.addValue("HotelAvailable", isAvailable);
		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;
		
	}
	@Override
	public Hotel getHotel(int hotelId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Hotel WHERE HotelId=?";
		Hotel hotel=null;
		try
		{
				hotel = jdbcTemplate.queryForObject(sql,
		                 hotelRowMapper,hotelId);
		}
		catch(Exception ex)
		{
			
		}
				return hotel;
	}
	@Override
	public List<Hotel> findAllHotels() {
		String sql = "SELECT * FROM Hotel";
		List<Hotel> hotelList=null;
		
				hotelList = jdbcTemplate.query(sql, 
		                 hotelRowMapper);
				return hotelList;
	}
	@Override
	public int deleteHotel(int hotelId) {
		roomDAO.deleteRooms(hotelId, 0);
		MapSqlParameterSource params;
		int rowsAffected;
		String sql = "delete from Hotel where HotelId=:id";
		
		params = new MapSqlParameterSource("id", hotelId);
		
		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;
	}
	@Override
	public int changeCityOfHotel(Hotel hotel, String newCity) {
		String sql = "update Hotel set Hotel_city=:newCity where HotelId=:id";
		int curTotalOrders, newTotalOrders;
		String prodName;
		MapSqlParameterSource params;
		int rowsAffected;
		int hotelId = hotel.getHotelId();
		
		params = new MapSqlParameterSource("id", hotelId);
		params.addValue("newCity", newCity);
		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;
	}
	@Override
	public String getHotelCity(int hotelId) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT Hotel_City FROM Hotel WHERE HotelId=?";
		String city="fremont";
		try
		{
			city = jdbcTemplate.queryForObject(sql,String.class,hotelId);
		}
		catch(Exception ex)
		{
			
		}
				return city;

	}

}
