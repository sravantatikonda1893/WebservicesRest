package com.java.sravan.WS_RSMessenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("parameter") String matrixParam,
			@HeaderParam("header") String header,
			@CookieParam("name") String cookieparam)
	{												
		return"matrixParam:"+matrixParam+"Header value:"+header+"Cookie value"+cookieparam;
		//	Matrix parameters:	  /annotations;parameter=sravan in req url
		//	Header params: should be passed using rest client UI boxes
		//	here, jersey internally converts these param values to required primituve types from string automatically

	}
//	When we have lot of parameters, then it will be messy. Jersey has given us UriInfo class which has methods for
//	invoking methods. And this has to be annotated with "@Context"
public String getParamsUsingContext(@Context UriInfo uriInfo
									,@Context HttpHeaders headers){
	String path=uriInfo.getAbsolutePath().toString();
	String cookies=headers.getCookies().toString();
	return "Path:"+path+"Cookies:"+cookies;
}


}
