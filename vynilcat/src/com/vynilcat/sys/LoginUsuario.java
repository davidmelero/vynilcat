package com.vynilcat.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sys_usuario")
public class LoginUsuario extends Usuario {

	
	@Column(name="currentAttempts")
	private Integer currentLoginAttempts;
	
	private boolean enabled;
	
	private boolean locked;
	
	public LoginUsuario(){
		
	}
	
	public LoginUsuario(NuevoUsuario nuevo){
		this.setAutoridades(nuevo.getAutoridades());
		this.setEmail(nuevo.getEmail());
		this.setFavoritos(nuevo.getFavoritos());
		this.setIdUsuario(nuevo.getIdUsuario());
		this.setName(nuevo.getName());
		this.setPassword(nuevo.getPassword());
		this.setRegistrado(nuevo.getRegistrado());
		this.setSurname(nuevo.getSurname());
		this.setUserName(nuevo.getUserName());
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}	
	
	public Integer getCurrentLoginAttempts() {
		return currentLoginAttempts;
	}
	
	public void setCurrentLoginAttempts(Integer currentLoginAttempts) {
		this.currentLoginAttempts = currentLoginAttempts;
	}

	public void resetCurrentLoginAttempts(){
		this.currentLoginAttempts = 0;
	}

	public void addLoginAttempt() {
		++this.currentLoginAttempts;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getCuenta(){
		for(Autoridad auth : super.getAutoridades()){
			switch(auth.getAutoridades()){
				case Autoridad.ROLE_ADMIN: return "Administrador";
				case Autoridad.ROLE_PREMIUM: return "Premium";
			}
		}
		
		return "Free";
	}
	
}
