package com.example.osahaneat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomFiterSecurity{

	@Autowired
	CustomUserDetailService customUserDetailService;

	@Autowired
	CustomJwtFilter customJwtFilter;
	
	

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {

		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity
				.getSharedObject(AuthenticationManagerBuilder.class);

		authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
		
		
		
		return authenticationManagerBuilder.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and()
		.csrf().disable()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
		.authorizeHttpRequests()
		.requestMatchers("/login/**","/restaurant/file/**", "/food/file/**")
		.permitAll()
		.anyRequest().authenticated();
		
		http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}
	

	
	
}
