package com.vynilcat.annotations;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.vynilcat.sys.NuevoUsuario;

public class CompareStringsValidator implements ConstraintValidator<CVCompareStrings, Object> {
	
	private String compare;
	
	private String compareTo;
	
	@Override
	public void initialize(CVCompareStrings constraintAnnotation) {
		this.compare = constraintAnnotation.compare();
		this.compareTo = constraintAnnotation.compareTo();
	}

	@Override
	public boolean isValid(Object target, ConstraintValidatorContext context) {
		
		if(target!=null){
			String[] values = new String[2];
			try {
				PropertyDescriptor descriptor = null;
				for(int i=0; i<2; i++){
					descriptor = new PropertyDescriptor(i==0?this.compare:this.compareTo, NuevoUsuario.class);
					values[i] = (String) descriptor.getReadMethod().invoke(target);
				}
				
				if(values[0]!=null && values[1]!=null && new BCryptPasswordEncoder().matches(values[1], values[0]))
					return true;
				
			} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

}
