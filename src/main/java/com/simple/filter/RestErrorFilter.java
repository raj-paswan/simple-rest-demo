package com.simple.filter;

import com.simple.domain.ErrorResponse;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class RestErrorFilter implements ContainerResponseFilter {

	private final static Logger LOGGER = Logger.getLogger(RestErrorFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx) {
		// hide stack-traces when any error occurs
		List<Response.Status.Family> errorFamily = Arrays.asList(Response.Status.Family.CLIENT_ERROR,
		                                                         Response.Status.Family.SERVER_ERROR);
		Response.StatusType statusType = responseCtx.getStatusInfo();
		Object responseEntity = responseCtx.getEntity();
		if (errorFamily.contains(statusType.getFamily()) && !(responseEntity instanceof ErrorResponse)) {
			LOGGER.severe("Error: " + responseEntity);
			responseCtx.setEntity(new ErrorResponse(Response.Status.fromStatusCode(statusType.getStatusCode()).name(),
			                                        statusType.getReasonPhrase()));
		}
	}
}
