package com.vynilcat.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Constraint(validatedBy = { CompareStringsValidator.class })
public abstract @interface CVCompareStrings{
	
	String compare();
	
	String compareTo();

	String message() default "Not equals";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
