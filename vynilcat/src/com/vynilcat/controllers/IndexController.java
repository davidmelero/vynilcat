package com.vynilcat.controllers;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vynilcat.data.UserRepository;
import com.vynilcat.sys.Autoridad;
import com.vynilcat.sys.LoginUsuario;
import com.vynilcat.sys.NuevoUsuario;
import com.vynilcat.sys.Search;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	Environment environment;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showIndexPage(){
		
		System.out.println(environment.getActiveProfiles());
		
		return "redirect:/browse";
	}

	@RequestMapping(value="browse", method=RequestMethod.GET)
	public @ResponseBody ModelAndView showBrowserPage(){
		ModelAndView model = new ModelAndView("browse");
		model.addObject(new Search());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getDetails());
		
		return model;
	}
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String showLoginPage(HttpServletRequest request, Model model){

		return "login";
	}

	@RequestMapping(value="register", method=RequestMethod.GET)
	public ModelAndView showRegisterPage(){
		ModelAndView model = new ModelAndView("register");
		model.addObject(new NuevoUsuario());
		
		return model;
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String processRegisterPage(RedirectAttributes model, @Valid NuevoUsuario newUser, Errors error){
		
		if(error.hasErrors())
			return "redirect:/register";
		
		// Parámetros que faltan en el formulario. 
		newUser.setRegistrado(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		newUser.setAutoridades(userRepository.getAutoridades(Autoridad.ROLE_USER));
		
		LoginUsuario logUser = new LoginUsuario(newUser);
		logUser.setEnabled(true);
		logUser.setCurrentLoginAttempts(0);
		
		String username = null;
		try{
			username = userRepository.save(logUser);
		}catch(DataIntegrityViolationException exc){
			model.addFlashAttribute("error", "El nombre de usuario o email ya existe. ");
		}catch(JDBCException exc){
			model.addFlashAttribute("error", "No se ha podido realizar el registro, inténtelo más tarde. ");
		}
		
		if(username!=null)
			model.addFlashAttribute("user", logUser);
		else
			return "redirect:/register";
		
		return "redirect:/profile/".concat(username);
	}
	
	@RequestMapping(value="error404", method=RequestMethod.GET)
	public String show404ErrorPage(){
		
		return "error404";
	}
	
	@RequestMapping(value="locked/{username}")
	public ModelAndView showLockedPage(@PathVariable("username") String username) {
		ModelAndView model = new ModelAndView("locked");
		model.addObject("username", username);
		
		return model;
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		System.out.println(enc.encode(args[0]));
	}
}
