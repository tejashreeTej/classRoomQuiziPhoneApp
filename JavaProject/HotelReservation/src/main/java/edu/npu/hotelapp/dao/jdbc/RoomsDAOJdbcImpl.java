package edu.npu.hotelapp.dao.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import edu.npu.hotelapp.dao.RoomsDAO;
import edu.npu.hotelapp.domain.Hotel;
import edu.npu.hotelapp.domain.Rooms;


@Repository("roomdao")
public class RoomsDAOJdbcImpl implements RoomsDAO {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private RoomsRowMapper roomsRowMapper;

	public RoomsDAOJdbcImpl() {
		//System.out.println("RoomsDAOJdbcImpl get created ");
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
		roomsRowMapper = new RoomsRowMapper();
		jdbcInsert = new SimpleJdbcInsert(dataSource)
		                 .withTableName("Rooms")
		                 .usingGeneratedKeyColumns("RoomId")
		                 .usingColumns("RoomType", "TotalRoom", "AvailableRooms","RoomCost","HotelId");
	}
	
	

	@Override
	public void insertRooms(Rooms room) {		
		SqlParameterSource params = new BeanPropertySqlParameterSource(room);
		
	    Number newId = jdbcInsert.executeAndReturnKey(params);
	    room.setRoomId(newId.intValue());	 
	    //System.out.println(room);
	}

	@Override
	public int getTotalRoomsofHotel(Hotel hotel, Rooms room) {
		int roomId=room.getRoomId();
		int hotelId=hotel.getHotelId();
		String sql = "SELECT TotalRoom FROM Rooms WHERE RoomId=? And HotelId=?";
		
				int totalroom = jdbcTemplate.queryForInt(sql,roomId,hotelId);	
				return totalroom;
	}

	@Override
	public int getAvailableRoomsofHotel(int hotelId, Rooms room) {
		int roomId=room.getRoomId();
		
		String sql = "SELECT AvailableRooms FROM Rooms WHERE RoomId=? And HotelId=?";
		
				int availableRoom = jdbcTemplate.queryForInt(sql,roomId,hotelId);	
				return availableRoom;

	}

	@Override
	public int getRoomCost(Rooms room) {
		// TODO Auto-generated method stub
		int roomId=room.getRoomId();
		String sql = "SELECT RoomCost FROM Rooms WHERE RoomId=?";
	
				int availableRoom = jdbcTemplate.queryForInt(sql,roomId);	
				return availableRoom;
	}

	@Override
	public int decrementAvailableRooms(Rooms room,int val) {
		// TODO Auto-generated method stub
		String sql = "update Rooms set AvailableRooms=:AvailableRooms where RoomId=:roomId";
		int AvailableRooms, roomId;
		MapSqlParameterSource params;
		int prodId, rowsAffected;
		
		AvailableRooms = room.getAvailableRooms();
		AvailableRooms = AvailableRooms - val;
		roomId = room.getRoomId();
		
		params = new MapSqlParameterSource("roomId", roomId);
		params.addValue("AvailableRooms", AvailableRooms);
		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;
	}

	@Override
	public int incremetAvailableRooms(Rooms room,int val) {
		// TODO Auto-generated method stub
		String sql = "update Rooms set AvailableRooms=:AvailableRooms where RoomId=:roomId";
		int AvailableRooms, roomId;
		MapSqlParameterSource params;
		int prodId, rowsAffected;
		
		AvailableRooms = room.getAvailableRooms();
		AvailableRooms = AvailableRooms + val;
		roomId = room.getRoomId();
		
		params = new MapSqlParameterSource("roomId", roomId);
		params.addValue("AvailableRooms", AvailableRooms);
		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;

	}

	@Override
	public List<Rooms> getAllRoomsOfHotel(int HotelId) {
		String sql = "SELECT * FROM Rooms WHERE HotelId=?";

		List<Rooms> roomsList=null;
		
		roomsList = jdbcTemplate.query(sql,roomsRowMapper,HotelId);
		
		/*for(Rooms room:roomsList){
			System.out.println(room);
		}*/
		
				return roomsList;
	}

	@Override
	public int getRoomsCount() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from Rooms";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}


	@Override
	public Rooms getRooms(int roomId) {
		String sql = "SELECT * FROM Rooms WHERE RoomId=?";
		Rooms room=null;
		
				room = jdbcTemplate.queryForObject(sql,
		                 roomsRowMapper,roomId);

				return room;

	}

	@Override
	public int deleteRooms(int hotelid, int roomid) {
		MapSqlParameterSource params;
		int rowsAffected;
		String sql;
		params = new MapSqlParameterSource("hotelid", hotelid);
		
		if(roomid<1)
		     sql = "delete from Rooms where HotelId=:hotelid";
		else
		{
			 sql = "delete from Rooms where HotelId=:hotelid AND RoomId=:roomid ";
		params.addValue("roomid", roomid);
		}
			
		rowsAffected = dbTemplate.update(sql, params);
		return rowsAffected;
	}
}
