package com.vynilcat.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.vynilcat.data.UserRepository;
import com.vynilcat.exceptions.CVUsernameNotFoundException;
import com.vynilcat.sys.Autoridad;
import com.vynilcat.sys.LoginUsuario;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	private UserRepository userRepository;

	public CustomUserDetailService(){

	}
	
	public CustomUserDetailService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws CVUsernameNotFoundException {
		
		LoginUsuario user = userRepository.findByUsername(username);
		if(user!=null){
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			
			for(Autoridad a : user.getAutoridades())
				authorities.add(new SimpleGrantedAuthority(a.getAutoridades()));
			
			return new User(user.getUserName(), user.getPassword(), user.isEnabled(), true, true, !user.isLocked(), authorities);	
		}else
			throw new CVUsernameNotFoundException();
	}

}
