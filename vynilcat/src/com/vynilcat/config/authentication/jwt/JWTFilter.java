package com.vynilcat.config.authentication.jwt;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Filter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;

@Filter(name = "JWTFilter")
public class JWTFilter extends GenericFilterBean {
    
	
    private AuthenticationEntryPoint entryPoint;
    	
	private AuthenticationProvider authenticationProvider;
    
	public JWTFilter(){
		 
	}

	public JWTFilter(AuthenticationProvider authenticationProvider, AuthenticationEntryPoint entryPoint){
		this.authenticationProvider = authenticationProvider;
		this.entryPoint = entryPoint;
	}
	
    @Override
    public void afterPropertiesSet() throws ServletException {
        Assert.notNull(authenticationProvider);
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        System.out.println("--> RECIBIDO! ");
        
        try {
            String stringToken = req.getHeader("Authorization");
            if (stringToken == null) {
                throw new InsufficientAuthenticationException("Authorization header not found");
            }
            
            // remove schema from token
            String authorizationSchema = "Bearer";
            if (stringToken.indexOf(authorizationSchema) == -1) {
                throw new InsufficientAuthenticationException("Authorization schema not found");
            }
            stringToken = stringToken.substring(authorizationSchema.length()).trim();
            
            try {
                JWT jwt = JWTParser.parse(stringToken);
                JWTToken token = new JWTToken(jwt);
            
                Authentication auth = authenticationProvider.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
                chain.doFilter(request, response);
            } catch (ParseException e) {
                throw new RuntimeException("Invalid token");
            }
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            if (entryPoint != null) {
                entryPoint.commence(req, res, e);
            }
        }    
    }
}
