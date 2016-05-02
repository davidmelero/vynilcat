package com.vynilcat.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
public class DataConfig {

	@Autowired
	private DataSource ds;
	
	@Bean
	@Profile(SecurityInitializer.PROFILE_DEVELOPMENT)
	public DataSource jdbcDataSource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("admin");
		ds.setUrl("jdbc:mysql://localhost:3306/vinilos");
		
		return ds;
	}
	
	@Bean
	@Profile(SecurityInitializer.PROFILE_PRODUCTION)
	public DataSource jndiDataSource(){
		JndiObjectFactoryBean jndi = new JndiObjectFactoryBean();
		jndi.setJndiName("java:/vinilos");
		jndi.setResourceRef(true);
		jndi.setProxyInterface(DataSource.class);
		
		return (DataSource) jndi.getObject();
	}
	
	@Bean
	public PlatformTransactionManager platformTransactionManager(SessionFactory sesionFactory){
		return new HibernateTransactionManager(sesionFactory);
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(ds);
		sessionFactory.setPackagesToScan("com.vynilcat","com.vynilcat.sys");
		Properties hibProps = new Properties();
		hibProps.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
		sessionFactory.setHibernateProperties(hibProps);
		
		return sessionFactory;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionProcessor(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
}
