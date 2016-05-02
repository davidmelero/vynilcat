package com.vynilcat.sys;

public class ContactMessageImpl implements ContactMessage{

	private Long idMessage;
	
	private Usuario user;
	
	private String title;
	
	private String body;

	public ContactMessageImpl(Usuario idUser, String title, String body) {
		this(null,idUser,title,body);
	}
	
	public ContactMessageImpl(Long idMessage, Usuario idUser, String title, String body) {
		this.idMessage = idMessage;
		this.user = idUser;
		this.title = title;
		this.body = body;
	}

	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario idUser) {
		this.user = idUser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Message [idMessage=" + idMessage + ", user=" + user + ", title=" + title + ", body=" + body + "]";
	}
	
}
