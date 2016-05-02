package com.vynilcat.sys;


import java.sql.Timestamp;

//@Entity
//@Table(name="sys_password_reset_token")
public class PasswordsResetToken {

	public static final int EXPIRATION = 60 * 24;
	
//	@Column(name="idToken")
	private Long id;
	
	private String token;
	
	private Timestamp expiryDate;
	
//	@OneToMany
//	@JoinColumn(name="idUser")
	private Long idUser;
	
	public PasswordsResetToken(){
		
	}
	
	public PasswordsResetToken(Long id, String token, Timestamp expiryDate){
		this.token = token;
		this.expiryDate = expiryDate;
	}
	
	public PasswordsResetToken(String token, Timestamp expiryDate){
		this(null, token, expiryDate);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
}
