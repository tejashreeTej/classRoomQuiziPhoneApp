package edu.npu.hotelapp.restclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import edu.npu.hotelapp.domain.Customer;

public class ResrvationServicesClient{
	static private Logger logger = Logger.getLogger(ResrvationServicesClient.class);
	private static String RESERVATION_SERVICES_URL = "http://localhost:8080/HotelReservation/hotelapp/webservices/reservation/";
	private static String RESERVATION_LOOKUP_URL = RESERVATION_SERVICES_URL + "{id}";
	private static Client client=null;  
	
	public static void main(String args[]) {
		testLookupReservation();  /* requires a Student to already be in the database -- set the id for the student to lookup in this method  */
		testPost();
	}
	
	private static Client getClient() {
		if (client == null) {
			client = ClientBuilder.newClient();
		}
		
		return client;
	}
	
	public static Customer testLookupReservation() {
		int idToLookup = 1; 
		int responseCode;
		Customer customer=null;
		
		Client client = getClient();
		
		//Targeting the RESTful Webservice we want to invoke by capturing it in WebTarget instance.
		WebTarget target = client.target(RESERVATION_LOOKUP_URL);
		target = target.resolveTemplate("id", idToLookup);
		
		// Now that we have the URI in the target, build the request i.e a GET request to the RESTful Webservice 
		Builder request = target.request(MediaType.APPLICATION_XML_TYPE);
		Response response = request.get();
		
		responseCode = response.getStatus();
		logger.info("The response code is: " + responseCode);
		if (responseCode == 200) {
			customer = response.readEntity(Customer.class);
			logger.info(customer);
		}
		
		return customer;
	}
	
	
	/* Using a POST Http Command, we'll add a completely new student */
	public static void testPost() {
		int responseCode;
		Customer newCustomer;
		Client client = getClient();
		
		newCustomer = createNewCustomer();
		
		WebTarget target = client.target(RESERVATION_SERVICES_URL);
		
		Builder request = target.request();
		request.accept(MediaType.APPLICATION_XML_TYPE);
		Response response = request.post(Entity.xml(newCustomer));
		
		responseCode = response.getStatus();
		logger.info("The response code from Post operation is: " + responseCode);
		
		if (responseCode == 200) {
			Customer createdCustomer = response.readEntity(Customer.class);
			logger.debug(createdCustomer);
		}
	}
	
	public static Customer createNewCustomer() {
		Customer cust;
		
		cust = new Customer();
	    cust.setCustFirstName("Tejashree");
	    cust.setCustLastName("Jagtap");
	    cust.setHotelId(1);
	    cust.setRoomId(2);
	    cust.setTotalCost(400);
		return cust;
	}


}

