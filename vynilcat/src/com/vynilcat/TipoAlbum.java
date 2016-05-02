package com.vynilcat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_album")
public class TipoAlbum {

	@Id
	@GeneratedValue
	int idTipoAlbum;
	
	String tipoAlbum;
	
	public TipoAlbum(){
		
	}
	
	public TipoAlbum(int idTipoAlbum, String tipoAlbum) {
		this();
		this.idTipoAlbum = idTipoAlbum;
		this.tipoAlbum = tipoAlbum;
	}

	public TipoAlbum(String tipoAlbum) {
		this();
		this.tipoAlbum = tipoAlbum;
	}
	
	public int getIdTipoAlbum() {
		return idTipoAlbum;
	}
	
	public void setIdTipoAlbum(int idTipoAlbum) {
		this.idTipoAlbum = idTipoAlbum;
	}
	
	public String getTipoAlbum() {
		return tipoAlbum;
	}
	
	public void setTipoAlbum(String tipoAlbum) {
		this.tipoAlbum = tipoAlbum;
	}
	
	@Override
	public String toString() {
		return "TipoAlbum [idTipoAlbum=" + idTipoAlbum + ", tipoAlbum=" + tipoAlbum + "]";
	}
	
}
