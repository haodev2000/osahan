package com.example.osahaneat.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.osahaneat.entity.Users;
import com.example.osahaneat.responsitory.UserResponsitory;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserResponsitory userResponsitory;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users u = userResponsitory.findByUsername(username);
		
		if(u == null) {
			throw new UsernameNotFoundException("User not exit");
		}
		return new User(username, u.getPassword(), new ArrayList<>());
		

	}

}
