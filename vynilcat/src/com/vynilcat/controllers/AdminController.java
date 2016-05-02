package com.vynilcat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vynilcat.services.AdminService;
import com.vynilcat.sys.LoginUsuario;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	
	@RequestMapping(method=RequestMethod.GET)
	public List<LoginUsuario> showAdminConsole() {
		
		return adminService.findAll();
	}
	
	@RequestMapping(value="/ban/{id}", method=RequestMethod.GET)
	public String banUser(@PathVariable("id") Long id) {
		
		adminService.updateActiveStatus(id);
		
		return "redirect:/admin";
	}
}
