package com.vynilcat.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class CVBadRequestException extends CVException{

	public CVBadRequestException(boolean fatalError){
		this(fatalError, null, null);
	}
	
	public CVBadRequestException(boolean fatalError, String objectName){
		this(fatalError,objectName,null);
	}
	
	public CVBadRequestException(boolean fatalError, String objectName, Map<String, String> fields){
		this.fatalError = fatalError;
		this.objectName = objectName;
		this.fieldErrors = fields;
	}

	@Override
	public String getMessage() {
		return HttpStatus.BAD_REQUEST.value()+" - Error en la petición recibida. ";
	}

}
