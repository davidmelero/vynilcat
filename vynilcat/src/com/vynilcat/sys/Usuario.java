package com.vynilcat.sys;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.vynilcat.Album;

@MappedSuperclass
public abstract class Usuario{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idUsuario")
	private Long idUsuario;
	
	@NotNull
	@Size(min=5, max=20)
	private String userName;
	
	@NotNull
	private String name;
	
	@NotNull
	private String surname;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Size(min=4)
	private String password;
	
	@Column(name="fechaRegistro")
	private Timestamp registrado;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name="sys_autorizados", joinColumns={@JoinColumn(name="fk_Usuario")}, inverseJoinColumns={@JoinColumn(name="fk_Autoridad")})
	private List<Autoridad> autoridades;

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name="favoritos", joinColumns={@JoinColumn(name="idUsuario")}, inverseJoinColumns={@JoinColumn(name="idAlbum")})
	private List<Album> favoritos;
	
	public Usuario(){
		
	}
	
	public Usuario(Long id, String userName, String name, String surname, String email, String password, Timestamp registrado){
		this.idUsuario = id;
		this.userName = userName;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.registrado = registrado;
	}
	
	public Usuario(String userName, String name, String surname, String email, String password, Timestamp registrado) {
		this(null, userName, name, surname, email, password, registrado);
	}

	public Long getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}
	
	public Timestamp getRegistrado() {
		return registrado;
	}
	
	public void setRegistrado(Timestamp registrado) {
		this.registrado = registrado;
	}

	public List<Autoridad> getAutoridades() {
		return autoridades;
	}

	public void setAutoridades(List<Autoridad> autoridades) {
		this.autoridades = autoridades;
	}
	
	public List<Album> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<Album> favoritos) {
		this.favoritos = favoritos;
	}
	
	
	
}
