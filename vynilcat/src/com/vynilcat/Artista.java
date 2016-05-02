package com.vynilcat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="artista")
public class Artista {

	public static final String TYPE = "artista"; 
	
	@Id
	@GeneratedValue
	int idArtista;
	
	@Column(name="nombreArtista")
	@NotNull
	@NotBlank
	String nombre;
	
	String url_biografia;
	
	public Artista(){
		
	}
	
	public Artista(int idArtista, String nombre) {
		this();
		this.idArtista = idArtista;
		this.nombre = nombre;
	}

	public Artista(String nombre) {
		this();
		this.nombre = nombre;
	}
	
	public int getIdArtista() {
		return idArtista;
	}
	
	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getUrl_biografia() {
		return url_biografia;
	}
	
	public void setUrl_biografia(String url_biografia) {
		this.url_biografia = url_biografia;
	}
	
	@Override
	public String toString() {
		return "Artista [idArtista=" + idArtista + ", nombre=" + nombre + ", url_biografia=" + url_biografia + "]";
	}
	
}
