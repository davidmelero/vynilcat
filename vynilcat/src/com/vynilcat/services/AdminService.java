package com.vynilcat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.vynilcat.data.UserRepository;
import com.vynilcat.sys.Autoridad;
import com.vynilcat.sys.LoginUsuario;

@Service
public class AdminService {

	@Autowired
	private UserRepository userRepository;

	@PreAuthorize(value = "hasRole('"+Autoridad.ROLE_ADMIN+"')")
	@PostFilter("filterObject.userName!=principal.username")
	public List<LoginUsuario> findAll(){
		return userRepository.findAll();
	}
	
	@PreAuthorize(value = "hasRole('"+Autoridad.ROLE_ADMIN+"')")
	public LoginUsuario updateActiveStatus(Long id) {
		
		LoginUsuario user = userRepository.findById(id);
		if(user!=null)
			user.setEnabled(!user.isEnabled());

		return userRepository.update(user);
	}

	@PreAuthorize(value = "hasRole('"+Autoridad.ROLE_ADMIN+"')")
	public LoginUsuario findById(Long id) {
		
		return userRepository.findById(id);
	}
}
