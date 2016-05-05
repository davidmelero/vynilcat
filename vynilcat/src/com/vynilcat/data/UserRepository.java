package com.vynilcat.data;

import java.util.List;

import com.vynilcat.sys.Autoridad;
import com.vynilcat.sys.ContactMessage;
import com.vynilcat.sys.LoginUsuario;
import com.vynilcat.sys.Usuario;

public interface UserRepository {

	List<LoginUsuario> findAll();

//	@Cacheable("vynilcatCache")
	LoginUsuario findByUsername(String username);
	
//	Collection<GrantedAuthority> getGrantedAuthorities(Usuario user);
	
	LoginUsuario findById(Long id);
	
	String save(Usuario user);
	
	LoginUsuario update(LoginUsuario user);
	
	void delete(LoginUsuario user);
	
	List<Autoridad> getAutoridades(String... auths);
	
	int addLoginAttempt(LoginUsuario user);
	
	void resetLoginAttempt(LoginUsuario user);

	ContactMessage contactAdmin(ContactMessage msg);
}
