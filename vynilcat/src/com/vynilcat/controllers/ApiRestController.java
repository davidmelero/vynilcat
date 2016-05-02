package com.vynilcat.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.vynilcat.data.UserRepository;
import com.vynilcat.sys.ContactMessage;

@RestController
@RequestMapping(value="/rest")
public class ApiRestController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/sendContact/{username}", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public @ResponseBody ResponseEntity<ContactMessage> sendMessageRest(@RequestBody ContactMessage cm) {
		
		ContactMessage cmResponse = userRepository.contactAdmin(cm);		
		URI uri = UriComponentsBuilder.fromPath("/profile").path("/{username}/contact").build().expand(cmResponse.getUser().getIdUsuario()).toUri();
		HttpHeaders header = new HttpHeaders();
		List<MediaType> accepts = new ArrayList<MediaType>();
		accepts.add(MediaType.APPLICATION_JSON);
		header.setAccept(accepts);
		header.setLocation(uri);
		
		return new ResponseEntity<ContactMessage>(cmResponse, header, HttpStatus.OK);
	}
	
}
