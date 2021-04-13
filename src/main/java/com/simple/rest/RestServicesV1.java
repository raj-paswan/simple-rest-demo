package com.simple.rest;

import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.logging.Logger;

@Path("/v1")
@Api(value = "Rest Services v1")
public class RestServicesV1 {

	private static final Logger LOGGER = Logger.getLogger(RestServicesV1.class.getName());

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/hello/{name}")
	public Response helloWorld(@PathParam("name") String name, @Context SecurityContext securityContext) {
		LOGGER.info("name: " + name);
		return Response.status(200).entity(String.format("Hello %s!", StringUtils.capitalize(name))).build();
	}

}
