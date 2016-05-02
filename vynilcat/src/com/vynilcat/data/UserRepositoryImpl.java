package com.vynilcat.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vynilcat.exceptions.CVUsernameNotFoundException;
import com.vynilcat.sys.Autoridad;
import com.vynilcat.sys.ContactMessage;
import com.vynilcat.sys.LoginUsuario;
import com.vynilcat.sys.Usuario;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LoginUsuario> findAll() {
		return getCurrentSession().createCriteria(LoginUsuario.class).addOrder(Order.asc("userName")).list();
	}

	@Override
	public LoginUsuario findByUsername(String username) {
		return (LoginUsuario) getCurrentSession().createCriteria(LoginUsuario.class).add(Restrictions.eq("userName", username)).setMaxResults(1).uniqueResult();
	}

	@Override
	public LoginUsuario findById(Long id) {
		return (LoginUsuario) getCurrentSession().createCriteria(LoginUsuario.class).add(Restrictions.idEq(id)).setMaxResults(1).uniqueResult();
	}

	@Override
	public String save(Usuario user) throws DataIntegrityViolationException {
		Long id = (Long) getCurrentSession().save(user);
		if(id!=null)
			return user.getUserName();
		
		return null;
	}

	@Override
	public LoginUsuario update(LoginUsuario user) throws DataIntegrityViolationException {
		getCurrentSession().update(user);
		return user;
	}

	@Override	
	public void delete(LoginUsuario user) throws JDBCException {
		getCurrentSession().delete(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Autoridad> getAutoridades(String... auths) {
		
		return getCurrentSession().createCriteria(Autoridad.class).add(Restrictions.in("autoridades", auths)).list();
	}

	@Override
	public int addLoginAttempt(LoginUsuario user) throws UsernameNotFoundException{
		
		if(user!=null){
			user.addLoginAttempt();
			getCurrentSession().update(user);	
		}else
			throw new CVUsernameNotFoundException();
		
		return user.getCurrentLoginAttempts();
	}

	@Override
	public void resetLoginAttempt(LoginUsuario user) throws UsernameNotFoundException{
		
		if(user!=null){
			user.resetCurrentLoginAttempts();
			getCurrentSession().update(user);
		}else
			throw new CVUsernameNotFoundException();
	}

	@Override
	public ContactMessage contactAdmin(ContactMessage msg) {
		
		System.out.println("--> " + msg.getBody());
		
		int numRows = getCurrentSession().getNamedQuery("insert into sys_contact_message values (:title,:body,:idUser)")
						.setString("title", msg.getTitle())
						.setString("body", msg.getBody())
						.setLong("idUser", msg.getUser().getIdUsuario())
						.executeUpdate();
		
		if(numRows==1)
			return msg;
		else 
			throw new HibernateException("Update error");
	}

//	@Override
//	public Collection<GrantedAuthority> getGrantedAuthorities(Usuario user) {
//		if(user!=null){
//			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//			for(Autoridad a : user.getAutoridades())
//				authorities.add(new SimpleGrantedAuthority(a.getAutoridades()));
//			
//			return authorities;
//		}
//		
//		return null;
//	}

}
