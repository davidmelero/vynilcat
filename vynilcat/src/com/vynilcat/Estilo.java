package com.vynilcat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estilo")
public class Estilo {

	@Id
	@GeneratedValue
	int idEstilo;
	
	String estilo;
	
	public Estilo(){
		
	}
	
	public Estilo(int idEstilo, String estilo) {
		this();
		this.idEstilo = idEstilo;
		this.estilo = estilo;
	}

	public Estilo(String estilo) {
		this();
		this.estilo = estilo;
	}

	public int getIdEstilo() {
		return idEstilo;
	}
	
	public void setIdEstilo(int idEstilo) {
		this.idEstilo = idEstilo;
	}
	
	public String getEstilo() {
		return estilo;
	}
	
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	
	@Override
	public String toString() {
		return "Estilo [idEstilo=" + idEstilo + ", estilo=" + estilo + "]";
	}
	
}
