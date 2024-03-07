package com.example.osahaneat.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.osahaneat.entity.Roles;
import com.example.osahaneat.entity.Users;
import com.example.osahaneat.payload.request.SigiupRequest;
import com.example.osahaneat.responsitory.UserResponsitory;
import com.example.osahaneat.service.impl.LoginServiceImpl;

@Service
public class LoginService implements LoginServiceImpl {

	@Autowired
	UserResponsitory userResponsitory;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public boolean checkLogin(String username, String password) {
		Users users = userResponsitory.findByUsername(username);
		
		return passwordEncoder.matches(password, users.getPassword());
	}

	@Override
	public boolean addUser(SigiupRequest sigiupRequest) {

		Users u = new Users();

		Roles role = new Roles();
		
		role.setId(sigiupRequest.getRoleId());
		
		u.setFullname(sigiupRequest.getFullname());
		u.setUsername(sigiupRequest.getUsername());
		u.setPassword(passwordEncoder.encode(sigiupRequest.getPassword()));
		u.setRoles(role);
		u.setCreateDate(new Date());
		try {
			userResponsitory.save(u);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
