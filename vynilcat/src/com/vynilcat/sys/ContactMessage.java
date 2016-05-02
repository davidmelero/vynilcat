package com.vynilcat.sys;

public interface ContactMessage {

	public Long getIdMessage();

	public void setIdMessage(Long idMessage);

	public Usuario getUser();

	public void setUser(Usuario idUser);

	public String getTitle();

	public void setTitle(String title);

	public String getBody();

	public void setBody(String body);
}
