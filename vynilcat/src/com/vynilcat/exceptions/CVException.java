package com.vynilcat.exceptions;

import java.util.Map;

@SuppressWarnings("serial")
public class CVException extends RuntimeException{

	protected boolean fatalError;
	
	protected String objectName;
	
	protected Map<String,String> fieldErrors;
	
	public void setFatalError(boolean fatalError) {
		this.fatalError = fatalError;
	}
	
	public boolean isFatalError() {
		return this.fatalError;
	}

	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(Map<String, String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
}
