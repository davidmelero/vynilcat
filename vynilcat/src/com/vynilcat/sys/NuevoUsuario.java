package com.vynilcat.sys;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vynilcat.annotations.CVCompareStrings;

@Entity
@Table(name="nuevousuario")
@CVCompareStrings(compare="password", compareTo = "repeatPassword")
public class NuevoUsuario extends Usuario{
	
	@NotNull
	@Size(min=4)
	@Transient // Indica que no es mapeable. 
	private String repeatPassword;
	
	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
}
