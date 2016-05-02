package com.vynilcat.data;

import java.util.List;

import com.vynilcat.Album;
import com.vynilcat.Artista;
import com.vynilcat.SelloDiscografico;

public interface DataRepository {

	List<Album> findAlbum(String searched);
	
	List<Album> findAlbumBySello(String searched);
	
	List<Artista> findArtista(String searched);
	
	List<Artista> findArtistaBySello(String searched);
	
	List<SelloDiscografico> findSello(String searched);
}
