package edu.npu.hotelapp.resthandlers;

import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import edu.npu.hotelapp.domain.Customer;
import edu.npu.hotelapp.domain.ReservationList;
import edu.npu.hotelapp.exceptions.DuplicateResourceException;
import edu.npu.hotelapp.exceptions.UnknownResourceException;
import edu.npu.hotelapp.services.HotelReservation;

@Path("/hotelapp")
public class ReservationRestHandler {

	@Autowired
	HotelReservation hotelreservation;
	
	private Logger logger = Logger.getLogger(ReservationRestHandler.class);
	
	/* Test Url:
	 * http://localhost:8080/webservices/hotelapp/reservation/1
	 * See web.xml file for Jersey configuration
	 */
	@GET
	@Path("/reservation/{id}")
	@Produces("application/xml, application/json")
	public Customer getReservation(@PathParam("id") int id) {
		Customer cust = null;
		
		try {
			System.out.println("Reservation getReservation() **1");
			cust=hotelreservation.getReservation(id);
			System.out.println("Reservation getReservation() **12");
			System.out.println("***Reservation*****"+cust);
		} catch (Exception ex) {
			System.out.println("ReservationList getReservation() ***67");
			throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
		
		if (cust == null) {
			logger.debug("Failed Request to lookup Customer with id  : " + id);
			throw new UnknownResourceException("Customer id: " + id + " is invalid");
		}
		
		return cust;
	}
	
	/* Test Url:
	 * http://localhost:8080/HotelReservation/webservices/hotelapp/reservation/
	 * See web.xml file for Jersey configuration
	 */
	@GET
	@Path("/reservation")
	@Produces("application/xml,application/json")
	public ReservationList getStudentList() {
		List<Customer> custList;
		ReservationList listOfReservations = new ReservationList();
		System.out.println("ReservationList getReservationList()");
		custList = hotelreservation.getReservationList();
		System.out.println("ReservationList getReservationList() 2");
		listOfReservations.setStudentList(custList);
		return listOfReservations;
	}
	
	
	@POST
	@Path("/reservation")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response addReservation(Customer newCustomer) {
		ResponseBuilder respBuilder;
		int id=newCustomer.getCustomerId();
		try { 
			
			hotelreservation.addNewReservation(newCustomer);
		} catch (Exception ex) {
			//throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
			throw new DuplicateResourceException("Can not add Reservation with id: " + id + " ");
		}
		
		logger.debug("Successfully created a new Customer: " + newCustomer);
		respBuilder = Response.status(Status.CREATED);
		respBuilder.entity(newCustomer);
		return respBuilder.build();
	}
	
	
	@PUT
	@Path("/reservation")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response updateReservation(Customer newCustomer) {
		ResponseBuilder respBuilder;
		
		try { 
			
			hotelreservation.updateReservation(newCustomer);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
		
		logger.debug("Successfully updated Customer: " + newCustomer);
		respBuilder = Response.status(Status.OK);
		respBuilder.entity(newCustomer);
		return respBuilder.build();
	}
	
	
	@DELETE
	@Path("/reservation/{id}")
	@Produces("application/json, application/xml")
	@Consumes("application/json, application/xml")
	public Response deleteReservation(@PathParam("id") int id) {
		ResponseBuilder respBuilder;
		
		try { 
			
			hotelreservation.deleteReservation(id);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
		
		logger.debug("Successfully deleted Reservations: ");
		respBuilder = Response.status(Status.OK);

		return respBuilder.build();
	}
}

