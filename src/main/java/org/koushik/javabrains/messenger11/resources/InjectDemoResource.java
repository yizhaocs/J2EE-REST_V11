package org.koushik.javabrains.messenger11.resources;

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

/*
 * REST Web Services 24 - Using Context and BeanParam annotatio
 * http://localhost:8080/messenger11/webapi/injectdemo/annotations;param=value
 * http://localhost:8080/messenger11/webapi/injectdemo/context
 * */
@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	// http://localhost:8080/messenger11/webapi/injectdemo/annotations;param=value
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("customHeaderValue") String header,
											@CookieParam("name") String cookie){
		return "Matrix param: " + matrixParam + " Header Param: " + header  + " Cookie Param: " + cookie ;
	}

	// http://localhost:8080/messenger11/webapi/injectdemo/context
	 @GET
	 @Path("context")
	 public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers){
	 	String path = uriInfo.getAbsolutePath().toString();
	 	String cookies = headers.getCookies().toString();
	 	return "Path : " + path + " Cookies: " + cookies;
	 }
}
