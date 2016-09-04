package edu.npu.hotelapp.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@XmlRootElement(name = "CustomerReservation")
public class Customer {
	@XmlElement(name = "customerId")
	private int customerId;
	@XmlElement(name = "custFirstName")
	private String custFirstName;
	@XmlElement(name = "custLastName")
	private String custLastName;
	@XmlElement(name = "totalCost")
	private double totalCost;
	@XmlElement(name = "HotelId")
	private int HotelId;
	@XmlElement(name = "RoomId")
	private int RoomId;
	//@XmlElement(name = "fromDate")
	//private Date fromDate;
	//@XmlElement(name = "toDate")
//	private Date toDate;
	@XmlElement(name = "noOfNights")
	private int noOfNights;
	
	

	public int getNoOfNights() {
		return noOfNights;
	}

	public void setNoOfNights(int noOfNights) {
		this.noOfNights = noOfNights;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String custFirstName, String custLastName) {
		super();
		this.custFirstName = custFirstName;
		this.custLastName = custLastName;
	}
	public Customer(String custFirstName, String custLastName,int custHotelId,int custRoomId, int noOfNights)
	{
		super();
		this.custFirstName = custFirstName;
		this.custLastName = custLastName;
		this.HotelId=custHotelId;
		this.RoomId=custRoomId;
		this.noOfNights=noOfNights;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustFirstName() {
		return custFirstName;
	}

	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	public String getCustLastName() {
		return custLastName;
	}

	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}


	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public int getHotelId() {
		return HotelId;
	}

	public void setHotelId(int custHotelId) {
		this.HotelId = custHotelId;
	}

	public int getRoomId() {
		return RoomId;
	}

	public void setRoomId(int custRoomId) {
		this.RoomId = custRoomId;
	}
	//@XmlElement(name = "fromDate", required = true) 
	//@XmlJavaTypeAdapter(DateXmlAdapter.class)  // This class has methods to convert a date to/from XML in the format we want
	//@JsonDeserialize(using = CustomJsonDateDeserializer.class)  /* How to convert a json date string to a date object */
	//@JsonSerialize(using = CustomJsonDateSerializer.class)      /* How to convert a date object to a JSON date string */
	
	//public Date getFromDate() {
		//return fromDate;
	//}

	//public void setFromDate(Date fromDate) {
		//this.fromDate = fromDate;
	//}
	//@XmlElement(name = "toDate", required = true) 
	//@XmlJavaTypeAdapter(DateXmlAdapter.class)  // This class has methods to convert a date to/from XML in the format we want
	//@JsonDeserialize(using = CustomJsonDateDeserializer.class)  /* How to convert a json date string to a date object */
	//@JsonSerialize(using = CustomJsonDateSerializer.class)      /* How to convert a date object to a JSON date string */
	
	//public Date getToDate() {
		//return toDate;
	//}

	//public void setToDate(Date toDate) {
		//this.toDate = toDate;
	//}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", custFirstName="
				+ custFirstName + ", custLastName=" + custLastName
				+ ", totalCost=" + totalCost + ", HotelId=" + HotelId
				+ ", RoomId=" + RoomId + ", noOfNights=" + noOfNights + "]";
	}

}
