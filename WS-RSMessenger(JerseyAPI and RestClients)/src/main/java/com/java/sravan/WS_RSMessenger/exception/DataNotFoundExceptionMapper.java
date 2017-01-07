package com.java.sravan.WS_RSMessenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.java.sravan.WS_RSMessenger.model.ErrorMessage;
//This class needed to map our exception to the response when there is an exception
@Provider
//This registers as a provider that this is available for exception mapper for a particular exception
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{
//As ExceptionMapper is a raw interface. we need to add the specific into that generic type
	@Override
//Its a way to catch the exception from jersey and packing and then returning it to response
	public Response toResponse(DataNotFoundException ex) {
//		return Response.status(Status.NOT_FOUND).build();
ErrorMessage errMsg=new ErrorMessage(ex.getMessage(), 404, "www.sravandocdesc.org");
return Response.status(Status.NOT_FOUND).entity(errMsg).build();
	
	}
	

}
