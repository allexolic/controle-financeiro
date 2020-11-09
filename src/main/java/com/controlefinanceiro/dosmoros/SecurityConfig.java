package com.controlefinanceiro.dosmoros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, proxyTargetClass=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService usuariosService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(usuariosService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http
			.headers()
				.frameOptions().sameOrigin()
				.and()
			.authorizeRequests()
				.antMatchers("/resources/**", "/webjars/**", "/vendor/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/contas").hasAnyRole("ADMIN", "USER")
				.antMatchers("/integracaoCompras").hasAnyRole("ADMIN", "USER")
				.antMatchers("/compras").hasAnyRole("ADMIN", "USER")
				.antMatchers("/dashboard").hasRole("ADMIN")
				.antMatchers("/usuarios").hasRole("ADMIN")
				.antMatchers("/fabricantes").hasRole("PARAM")
				.antMatchers("/produtos").hasRole("PARAM")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/controleFinanceiro")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
