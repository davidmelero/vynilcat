package com.vynilcat.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vynilcat.exceptions.CVBadRequestException;
import com.vynilcat.exceptions.CVNotAcceptableException;
import com.vynilcat.exceptions.CVNotFoundException;
import com.vynilcat.exceptions.CVUsernameNotFoundException;

@ControllerAdvice
public class HandlerExceptionsController {

	@ExceptionHandler(CVBadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody CVBadRequestException handleBadRequestException(CVBadRequestException exc){
		return exc;
	}
	
	@ExceptionHandler(CVNotAcceptableException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public @ResponseBody CVNotAcceptableException handleNotAcceptableException(CVNotAcceptableException exc) {
		return exc;
	}
	
	@ExceptionHandler(CVNotFoundException.class)
	public String handleNotFoundException(CVNotFoundException exc) {
		return "404";
	}
	
	@ExceptionHandler(LockedException.class)
	public String handleLockedException(LockedException exc) {
		return "/";
	}
	
	@ExceptionHandler(CVUsernameNotFoundException.class)
	public String handleUnauthorizedException(CVUsernameNotFoundException exc) {
		return "/login";
	}

}
