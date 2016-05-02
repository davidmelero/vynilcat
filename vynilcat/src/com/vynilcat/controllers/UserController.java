package com.vynilcat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vynilcat.data.UserRepository;
import com.vynilcat.services.RestService;
import com.vynilcat.sys.ContactMessage;
import com.vynilcat.sys.ContactMessageImpl;

@Controller
@RequestMapping(value="/profile")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestService restService;
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public ModelAndView showProfilePage(@PathVariable("username") String username){
		ModelAndView model = new ModelAndView("profile");
		model.addObject("user", userRepository.findByUsername(username));
		
		return model;
	}
	
	@RequestMapping(value="/{username}/upgrade", method=RequestMethod.GET)
	public ModelAndView showUpgradePage(@PathVariable("username") String username) {
		ModelAndView model = new ModelAndView("upgrade");
		
		return model;
	}
	
	@RequestMapping(value="/{username}/contact", method=RequestMethod.GET)
	public String showMessagePage(@PathVariable("username") String username) {
		
		return "contact";
	}
	
	@RequestMapping(value="/{username}/contact", method=RequestMethod.POST)
	public String processMessagePage(RedirectAttributes model, @PathVariable("username") String username, 
										   @RequestParam("title") String title, @RequestParam("body") String body) {
		
		
		
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		if(username.equals(currentUser)){
			ContactMessage cm = new ContactMessageImpl(userRepository.findByUsername(currentUser), title, body);
			ResponseEntity<ContactMessage> response = restService.send(cm);
			if(response!=null && response.getStatusCode()==HttpStatus.OK)
				model.addFlashAttribute("message", "Se ha enviado correctamente. ");
			else
				model.addFlashAttribute("message", "Error en el envío, pruebe más tarde. ");
		}
		
		return "redirect:/profile/{username}";
	}
	
}
