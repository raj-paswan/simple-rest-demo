package com.simple.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

/**
 * Do Post processing of the rest response. This is registered for all the rest
 * responses so for each Rest API response, the filter function is going to be
 * called.
 *
 * @author kishu
 */
//@Secured
@Provider
@PreMatching
public class RestResponseFilter implements ContainerResponseFilter {

	private final static Logger LOGGER = Logger.getLogger(RestResponseFilter.class.getName());

	@Override
	public void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx) {
		LOGGER.info("Inside rest response filter");
		String path = requestCtx.getUriInfo().getPath();
		LOGGER.info("Filtering REST Response " + path);

		String host = requestCtx.getHeaderString("Origin");
		MultivaluedMap<String, Object> responseHeaders = responseCtx.getHeaders();
		responseHeaders.add("Access-Control-Allow-Origin", host);
		responseHeaders.add("Access-Control-Allow-Credentials", "true");
		responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT,OPTIONS");
		responseHeaders.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");

		// Add Strict-Transport-Security header to all the api responses
		// Set the max age to 1 day for now
		responseHeaders.add("Strict-Transport-Security", "max-age=86400; includeSubDomains;");

		// Add X-Content-Type-Options header in all rest api responses. This will ensure that the browsers/clients
		// won't try to MIME-sniff the api response. MIME-sniff means that the Content-Type returned by the server
		// says it's text/html but the browser/client will try to guess the MIME type from the response body and
		// assumes it's a gif image, for example.
		responseHeaders.add("X-Content-Type-Options", "nosniff");

		responseHeaders.add("Content-Security-Policy",
		                    "script-src 'self' 'unsafe-eval' *.crisp.chat *.crisp.im *.googleapis.com *.g.doubleclick.net *.tagmanager.google.com *.googletagmanager.com *.google-analytics.com *.bootstrapcdn.com cdn.jsdelivr.net code.jquery.com unpkg.com cdnjs.cloudflare.com");

		LOGGER.info("Request Header" + requestCtx.getHeaders());
	}

}
