package com.vynilcat.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vynilcat.SelloDiscografico;
import com.vynilcat.data.DataRepository;
import com.vynilcat.exceptions.CVBadRequestException;
import com.vynilcat.exceptions.CVException;
import com.vynilcat.exceptions.CVNotAcceptableException;
import com.vynilcat.sys.Result;
import com.vynilcat.sys.Search;

@RequestMapping("/search")
@RestController
public class SearchController {
	
	@Autowired
	private DataRepository data;
	
	@RequestMapping(value="/all", method=RequestMethod.POST, consumes="application/json")
	public Result search(@RequestBody @Valid Search searched, Errors errors) throws CVException{
 
		getException(errors, searched);
		
		Result mySearch = new Result(searched.getSearched());
		
		String searchedCad = "%".concat(searched.getSearched()).concat("%").toLowerCase();
		
		mySearch.setAlbumes(data.findAlbum(searchedCad));
		mySearch.setArtistas(data.findArtista(searchedCad));
		
		List<SelloDiscografico> sellos = data.findSello(searchedCad);
		mySearch.setSellos(sellos);
		if(sellos.size()>0){
			mySearch.addAlbumes(data.findAlbumBySello(searchedCad));
			mySearch.addArtistas(data.findArtistaBySello(searchedCad));
		}
		
		return mySearch;
	}
	
	private void getException(Errors errors, Search searched) throws CVException{
		if(errors.hasErrors()){
			HashMap<String,String> fields = new HashMap<String,String>();
			for(ObjectError err : errors.getAllErrors())
				fields.put(err.getObjectName(), err.getDefaultMessage());
			throw new CVNotAcceptableException(false, errors.getObjectName(), fields);
		}
		
		if(searched==null || searched.getSearched()==null)
			throw new CVBadRequestException(true);
	}
	
}
