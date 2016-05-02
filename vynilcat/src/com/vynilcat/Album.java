package com.vynilcat;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="album")
public class Album{
	
	public static final String TYPE = "album"; 
	
	@Id
	@GeneratedValue
	private int idAlbum;
	
	@Column(name="nombreAlbum")
	@NotNull
	private String nombre;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name="album_and_artista", joinColumns={@JoinColumn(name="fk_album")}, inverseJoinColumns={@JoinColumn(name="fk_artista")})
	private List<Artista> artista;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name="album_and_estilo", joinColumns={@JoinColumn(name="fk_album")}, inverseJoinColumns={@JoinColumn(name="fk_estilo")})
	private List<Estilo> estilo;
	
	@ManyToOne
	@JoinColumn(name="fk_tipo_album")
	private TipoAlbum tipo_album;
	
	@ManyToOne
	@JoinColumn(name="fk_sello")
	private SelloDiscografico sello;

	@ManyToOne
	@JoinColumn(name="fk_condicion")
	private Condicion condicion;
	
	private String descripcion;
	
	private String portada;
	
	private LocalDate anyo_edicion;
	
	private String url_resenya;
	
	public Album(){
		
	}
	
	public Album(int idAlbum, String nombre, List<Artista> artista) {
		this();
		this.idAlbum = idAlbum;
		this.nombre = nombre;
		this.artista = artista;
	}

	public Album(String nombre, List<Artista> artista) {
		this();
		this.nombre = nombre;
		this.artista = artista;
	}
	
	public int getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Artista> getArtista() {
		return artista;
	}

	public void setArtista(List<Artista> artista) {
		this.artista = artista;
	}

	public List<Estilo> getEstilo() {
		return estilo;
	}

	public void setEstilo(List<Estilo> estilo) {
		this.estilo = estilo;
	}

	public TipoAlbum getTipo_album() {
		return tipo_album;
	}

	public void setTipo_album(TipoAlbum tipo_album) {
		this.tipo_album = tipo_album;
	}

	public SelloDiscografico getSello() {
		return sello;
	}

	public void setSello(SelloDiscografico sello) {
		this.sello = sello;
	}

	public Condicion getCondicion() {
		return condicion;
	}

	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPortada() {
		return portada;
	}
	
	public void setPortada(String portada) {
		this.portada = portada;
	}

	public LocalDate getAnyo_edicion() {
		return anyo_edicion;
	}

	public void setAnyo_edicion(LocalDate anyo_edicion) {
		this.anyo_edicion = anyo_edicion;
	}

	public String getUrl_resenya() {
		return url_resenya;
	}

	public void setUrl_resenya(String url_resenya) {
		this.url_resenya = url_resenya;
	}

	@Override
	public String toString() {
		return "Album [idAlbum=" + idAlbum + ", nombre=" + nombre + ", artista=" + artista + ", estilo=" + estilo
				+ ", tipo_album=" + tipo_album + ", sello=" + sello + ", condicion=" + condicion + ", descripcion="
				+ descripcion + ", portada=" + portada + ", anyo_edicion=" + anyo_edicion + ", url_resenya=" + url_resenya
				+ "]";
	}
	
}
