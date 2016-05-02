package com.vynilcat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SuppressWarnings("serial")
public class CVUsernameNotFoundException extends UsernameNotFoundException{
	
	private final static String message = HttpStatus.UNAUTHORIZED.value()+" - Usuario no válido. ";
	
	public CVUsernameNotFoundException() {
		super(message);
	}

	@Override
	public String getMessage() {
		return message;
	}

}
