package com.vynilcat.sys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sys_autoridades")
public class Autoridad {

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	public static final String ROLE_PREMIUM = "ROLE_PREMIUM";
	
	public static final String ROLE_USER = "ROLE_USER";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idAutoridad;
	
	@NotNull
	private String autoridades;
	
	public Autoridad() {
		
	}
	
	public Autoridad(Integer idAutoridad, String autoridades){
		this.idAutoridad = idAutoridad;
		this.autoridades = autoridades;
	}
	
	public Autoridad(String autoridades){
		this(null, autoridades);
	}

	public Integer getIdAutoridad() {
		return idAutoridad;
	}

	public void setIdAutoridad(Integer idAutoridad) {
		this.idAutoridad = idAutoridad;
	}

	public String getAutoridades() {
		return autoridades;
	}

	public void setAutoridades(String autoridades) {
		this.autoridades = autoridades;
	}

	@Override
	public String toString() {
		return "Autoridad [idAutoridad=" + idAutoridad + ", autoridades=" + autoridades + "]";
	}
	
}
