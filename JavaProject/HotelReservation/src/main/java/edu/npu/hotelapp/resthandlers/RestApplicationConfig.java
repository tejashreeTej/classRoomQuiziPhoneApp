package edu.npu.hotelapp.resthandlers;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

import edu.npu.hotelapp.exceptions.DuplicateResourceExResolver;
import edu.npu.hotelapp.exceptions.UnknownResourceExResolver;


/*   See web.xml file for Jersey configuration  */
/*   Need to register classes with @PATH annotations and other JAX-RS components */
@ApplicationPath("/")
public class RestApplicationConfig extends Application {
	private Set<Class<?>> restClassSet = new HashSet<Class<?>>();
	
	public RestApplicationConfig() {
		restClassSet.add(JacksonFeature.class);
		restClassSet.add(ReservationRestHandler.class);
	    restClassSet.add(UnknownResourceExResolver.class);
	    restClassSet.add(DuplicateResourceExResolver.class);
	}
	
	public Set<Class<?>> getClasses() {
		return restClassSet;
	}
}
