package com.vynilcat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.vynilcat.config.authentication.CustomAuthenticationFailureHandler;
import com.vynilcat.config.authentication.CustomAuthenticationSuccessHandler;
import com.vynilcat.data.UserRepository;
import com.vynilcat.services.CustomUserDetailService;
import com.vynilcat.sys.Autoridad;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
//	@Autowired
//	private DataSource ds;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new CustomUserDetailService(userRepository))
			.passwordEncoder(new BCryptPasswordEncoder());
				
//			.jdbcAuthentication()
//				.dataSource(this.ds)
//				.usersByUsernameQuery("select userName, password, enabled from sys_usuario where userName=?")
//				.authoritiesByUsernameQuery("select u.userName, a.autoridades from sys_usuario u inner join sys_autorizados on idUsuario=fk_Usuario inner join sys_autoridades a on fk_Autoridad=idAutoridad where u.userName=?")
//				.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers("/","/error404","/register/**,/locked/**").permitAll()
				.antMatchers("/browse","/search/**","/profile/**").authenticated()
				.antMatchers("/admin/**").fullyAuthenticated()
				.antMatchers("/admin/**").hasAuthority(Autoridad.ROLE_ADMIN)
				.anyRequest().permitAll()
			.and()
				.rememberMe()
				.tokenValiditySeconds(2419200)
			.and()
				.formLogin()
					.loginPage("/login").permitAll()
					.passwordParameter("pass")
					.usernameParameter("user")
					.loginProcessingUrl("/login")
					.failureHandler(customAuthenticationFailureHandler())
					.successHandler(customAuthenticationSuccessHandler())
			.and()
				.logout()
					.logoutSuccessUrl("/");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {		
		return super.authenticationManagerBean();
	}
	
	@Bean
	public AuthenticationFailureHandler customAuthenticationFailureHandler(){
		return new CustomAuthenticationFailureHandler();
	}

	@Bean
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler(){
		CustomAuthenticationSuccessHandler authSuccess = new CustomAuthenticationSuccessHandler();
		authSuccess.setUseReferer(true);
		
		return authSuccess;
	}
	
}
