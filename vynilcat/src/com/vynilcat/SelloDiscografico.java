package com.vynilcat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sello_discografico")
public class SelloDiscografico {

	@Id
	@GeneratedValue
	@Column(name="idSello")
	int idSello;
	
	String sello;
	
	public SelloDiscografico(){
		
	}
	
	public SelloDiscografico(int idSello, String sello) {
		this();
		this.idSello = idSello;
		this.sello = sello;
	}

	public SelloDiscografico(String sello) {
		this();
		this.sello = sello;
	}
	
	public int getIdSello() {
		return idSello;
	}
	
	public void setIdSello(int idSello) {
		this.idSello = idSello;
	}
	
	public String getSello() {
		return sello;
	}
	
	public void setSello(String sello) {
		this.sello = sello;
	}

	@Override
	public String toString() {
		return "SelloDiscografico [idSello=" + idSello + ", sello=" + sello + "]";
	}
	
}
