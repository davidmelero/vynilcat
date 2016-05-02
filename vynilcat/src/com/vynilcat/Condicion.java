package com.vynilcat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="condicion")
public class Condicion {

	@Id
	@GeneratedValue
	int idCondicion;
	
	String condicion;
	
	String descripcion;
	
	public Condicion(){
		
	}
	
	public Condicion(int idCondicion, String condicion, String descripcion) {
		this();
		this.idCondicion = idCondicion;
		this.condicion = condicion;
		this.descripcion = descripcion;
	}

	public Condicion(String condicion, String descripcion) {
		this();
		this.condicion = condicion;
		this.descripcion = descripcion;
	}
	
	public int getIdCondicion() {
		return idCondicion;
	}
	
	public void setIdCondicion(int idCondicion) {
		this.idCondicion = idCondicion;
	}
	
	public String getCondicion() {
		return condicion;
	}
	
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "Condicion [idCondicion=" + idCondicion + ", condicion=" + condicion + ", descripcion=" + descripcion
				+ "]";
	}
	
	
	
}
