package com.simple.app;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {

	public MyApplication() {
		register(new MyApplicationBinder());
		packages(true, "io.swagger.jaxrs.listing;com.simple.rest;com.simple.filter.rest;");
		register(MultiPartFeature.class);
	}
}
