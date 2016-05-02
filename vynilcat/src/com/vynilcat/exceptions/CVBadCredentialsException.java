package com.vynilcat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;

@SuppressWarnings("serial")
public class CVBadCredentialsException extends BadCredentialsException {
	
	public CVBadCredentialsException(String msg) {
		super(message);
	}

	private final static String message = HttpStatus.UNAUTHORIZED.value()+" - Usuario no válido. ";
	
	@Override
	public String getMessage() {
		return message;
	}
}
