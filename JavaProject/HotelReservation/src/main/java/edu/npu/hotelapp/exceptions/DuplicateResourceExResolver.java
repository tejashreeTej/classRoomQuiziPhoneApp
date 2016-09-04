package edu.npu.hotelapp.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class DuplicateResourceExResolver implements ExceptionMapper<DuplicateResourceException> {
 
    @Override
    public Response toResponse(DuplicateResourceException ex) {
    	ResponseBuilder respBuilder;
        Status httpStatus = Status.CONFLICT;
        
        respBuilder = Response.status(httpStatus);
        respBuilder.entity(ex.getMessage());
        respBuilder.type(MediaType.TEXT_PLAIN);
        return respBuilder.build();
    }

}
