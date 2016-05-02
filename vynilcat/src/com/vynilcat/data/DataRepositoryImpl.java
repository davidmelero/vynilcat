package com.vynilcat.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vynilcat.Album;
import com.vynilcat.Artista;
import com.vynilcat.SelloDiscografico;

@Repository
@Transactional
public class DataRepositoryImpl implements DataRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Album> findAlbum(String searched) {
		return getCurrentSession()
					.createSQLQuery("Select distinct album.* From album inner join album_and_artista on fk_album=idAlbum inner join artista on fk_artista=idArtista Where nombreArtista like :searched or nombreAlbum like :searched order by nombreAlbum asc")
					.addEntity(Album.class)
					.setParameter("searched", searched)
					.list();
	
//		return getCurrentSession().createCriteria(Album.class).add(Restrictions.like("nombre", "%".concat(searched).concat("%"))).list();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Artista> findArtista(String searched) {
				
		return getCurrentSession()
					.createSQLQuery("Select distinct artista.* From artista inner join album_and_artista on fk_artista=idArtista inner join album on fk_album=idAlbum Where nombreArtista like :searched or nombreAlbum like :searched order by nombreArtista asc")
					.addEntity(Artista.class)
					.setParameter("searched", searched)
					.list();

//		return getCurrentSession().createCriteria(Artista.class).add(Restrictions.like("nombre", "%".concat(searched).concat("%"))).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SelloDiscografico> findSello(String searched) {
		return getCurrentSession()
					.createSQLQuery("Select distinct sello_discografico.* From sello_discografico inner join album on fk_sello=idSello inner join album_and_artista on fk_album=idAlbum inner join artista on fk_artista=idArtista Where album.nombreAlbum like :searched or sello like :searched or nombreArtista like :searched order by sello asc")
					.addEntity(SelloDiscografico.class)
					.setParameter("searched", searched)
					.list();
		
//		return getCurrentSession().createCriteria(SelloDiscografico.class).add(Restrictions.like("sello", "%".concat(searched).concat("%"))).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Album> findAlbumBySello(String searched) {
		return getCurrentSession().createSQLQuery("Select distinct album.* From album inner join sello_discografico on fk_sello=idSello Where sello like :searched order by nombreAlbum asc")
					.addEntity(Album.class)
					.setParameter("searched", searched)
					.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Artista> findArtistaBySello(String searched) {
		
		return getCurrentSession().createSQLQuery("Select distinct artista.* From artista inner join album_and_artista on fk_artista=idArtista inner join album on fk_album=idAlbum inner join sello_discografico on fk_sello=idSello Where sello like :searched order by nombreArtista asc;")
					.addEntity(Artista.class)
					.setParameter("searched", searched)
					.list();				
	}

}
