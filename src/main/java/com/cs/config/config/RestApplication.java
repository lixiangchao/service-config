package com.cs.config.config;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig {
	public RestApplication() {
		packages("com.cs.config.api");
		register(JacksonJsonProvider.class);
	}
}
