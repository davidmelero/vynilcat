package com.vynilcat.config.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.vynilcat.data.UserRepository;
import com.vynilcat.exceptions.CVUsernameNotFoundException;
import com.vynilcat.sys.LoginUsuario;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		saveException(request, exception);
		String username = request.getParameter("user");
		if(username!=null){
			LoginUsuario user = userRepository.findByUsername(username);
			if(userRepository.addLoginAttempt(user)<5)
				getRedirectStrategy().sendRedirect(request, response, "/login?error");
			else
				getRedirectStrategy().sendRedirect(request, response, "/locked/"+username);	
		}else
			throw new CVUsernameNotFoundException();
	}
}
