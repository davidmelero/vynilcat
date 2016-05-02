package com.vynilcat.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.vynilcat.config.authentication.jwt.JWTAuthenticationProvider;
import com.vynilcat.config.authentication.jwt.JWTFilter;

@Configuration
@ComponentScan(basePackages="com.vynilcat")
public class WebInitializer implements WebApplicationInitializer{
	
//	@Autowired
//	AuthenticationEntryPoint authenticationEntryPoint;
//	
//	@Autowired
//	AuthenticationProvider authenticationProvider;
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		// Declaración del contexto Root
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootConfig.class);
		
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		// Declaración del contexto Web 
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(WebConfig.class);
		
		// Creamos el DispatcherServlet, lo registramos y asignamos a '/'. 
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		// Filtro para los controladores Rest
		FilterRegistration.Dynamic rest = servletContext.addFilter("JWTFilter", new JWTFilter(authenticationProvider(),authenticationEntryPoint()));
		rest.addMappingForUrlPatterns(null, false, "/rest/*");
		
		rootContext.getEnvironment().setActiveProfiles(SecurityInitializer.PROFILE_DEVELOPMENT);
	}
	
	private AuthenticationProvider authenticationProvider(){
		return new JWTAuthenticationProvider();
	}
	
	private AuthenticationEntryPoint authenticationEntryPoint(){
		BasicAuthenticationEntryPoint authEntryPoint = new BasicAuthenticationEntryPoint();
		authEntryPoint.setRealmName("Security API VynilCat");
		
		return authEntryPoint;
	}
}
