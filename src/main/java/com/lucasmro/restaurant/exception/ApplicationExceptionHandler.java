package com.lucasmro.restaurant.exception;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationException> 
{
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(ApplicationException exception) 
    {
    	Map<String, Object> error = new HashMap<String, Object>();
    	error.put("code", Status.BAD_REQUEST.getStatusCode());
    	error.put("message", exception.getMessage());

        return Response.status((int) error.get("code")).entity(error).build();  
    }
}
