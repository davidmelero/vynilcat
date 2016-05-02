package com.vynilcat.config.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.vynilcat.data.UserRepository;
import com.vynilcat.exceptions.CVUsernameNotFoundException;
import com.vynilcat.sys.LoginUsuario;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		String username = request.getParameter("user");
		if(username!=null){
			LoginUsuario user = userRepository.findByUsername(username);
			userRepository.resetLoginAttempt(user);
			
			getRedirectStrategy().sendRedirect(request, response, "/");
		}else
			throw new CVUsernameNotFoundException();
	}
	
}
