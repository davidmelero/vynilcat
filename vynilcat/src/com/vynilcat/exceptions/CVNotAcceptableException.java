package com.vynilcat.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class CVNotAcceptableException extends CVException{

	public CVNotAcceptableException(boolean fatalError){
		this(fatalError,null,null);
	}
	
	public CVNotAcceptableException(boolean fatalError, String objectName){
		this(fatalError,objectName,null);
	}
	
	public CVNotAcceptableException(boolean fatalError, String objectName, Map<String, String> fields){
		this.fatalError = fatalError;
		this.fieldErrors = fields;
		this.objectName = objectName;
	}
	
	@Override
	public String getMessage() {
		return HttpStatus.NOT_ACCEPTABLE.value()+" - Parámetros insuficientes. ";
	}

}
