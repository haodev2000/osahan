package com.example.osahaneat.security;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.osahaneat.utils.JwtUtilsHelper;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class CustomJwtFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtilsHelper jwtUtilsHelper;
	
	@Autowired
	private UserDetailsService myUserDetails;
	
	@Value("${jwt.privateKey}")
	private String privateKey;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		
		String token = getTokenFromHeader(request);
		
		if(token != null) {
			
			if(jwtUtilsHelper.verifyToken(token)){
				
//				UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
				= new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(usernamePasswordAuthenticationToken);
		}
		}
		
		// phải để dưới này
		filterChain.doFilter(request, response);
		
	}
		
	public String getUsername(String token) {
		
		return Jwts.parser().setSigningKey(privateKey).build().parseClaimsJws(token).getBody().getSubject();
	}
	

	private String getTokenFromHeader(HttpServletRequest request) {
		String header = request.getHeader("Authorization");

		String token = null;

		if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
			token = header.substring(7);

		}

		return token;

	}

}
