package com.vynilcat.sys;

import java.util.List;

import com.vynilcat.Album;
import com.vynilcat.Artista;
import com.vynilcat.SelloDiscografico;

public class Result {

	private String searched;
	
	private List<Album> albumes;
	
	private List<Artista> artistas;
	
	private List<SelloDiscografico> sellos;
	
	public Result(){
	
	}
	
	public Result(String searched){
		this(searched, null, null, null);
	}
	
	public Result(String searched, List<Album> albumes){
		this(searched, albumes, null, null);
	}
	
	public Result(String searched, List<Album> albumes, List<Artista> artistas){
		this(searched, albumes, artistas, null);
	}
	
	public Result(String searched, List<Album> albumes, List<Artista> artistas, List<SelloDiscografico> sellos){
		this.searched = searched;
		this.albumes = albumes;
		this.artistas = artistas;
		this.sellos = sellos;
	}

	public String getSearched() {
		return searched;
	}

	public void setSearched(String searched) {
		this.searched = searched;
	}

	public List<Album> getAlbumes() {
		return albumes;
	}

	public void setAlbumes(List<Album> albumes) {
		this.albumes = albumes;
	}
	
	public void addAlbumes(List<Album> albumes) {
		this.albumes.addAll(albumes);
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}
	
	public void addArtistas(List<Artista> artistas) {
		this.artistas.addAll(artistas);
	}

	public List<SelloDiscografico> getSellos() {
		return sellos;
	}

	public void setSellos(List<SelloDiscografico> sellos) {
		this.sellos = sellos;
	}

	@Override
	public String toString() {
		return "Search [searched=" + searched + ", albumes=" + albumes + ", artistas=" + artistas + ", sellos=" + sellos
				+ "]";
	}

}