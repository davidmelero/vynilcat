package com.vynilcat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer{
	
	public static final String PROFILE_DEVELOPMENT = "development";
	
	public static final String PROFILE_PRODUCTION = "production";
	
}
