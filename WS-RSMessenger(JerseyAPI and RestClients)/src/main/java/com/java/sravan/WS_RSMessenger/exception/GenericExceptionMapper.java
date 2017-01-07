package com.java.sravan.WS_RSMessenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.java.sravan.WS_RSMessenger.model.ErrorMessage;
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	//	For all the exceptions this is going to be called as this is a generic exception
	public Response toResponse(Throwable ex) {


		ErrorMessage errMsg=new ErrorMessage(ex.getMessage(), 505, "www.sravandocdesc.org");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errMsg).build();
	}


}
//If we want to map specific exceptions we need to disable it before mapping by removing provider annotation