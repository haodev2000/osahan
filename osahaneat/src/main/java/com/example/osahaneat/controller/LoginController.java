package com.example.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.osahaneat.payload.ResponData;
import com.example.osahaneat.payload.request.SigiupRequest;
import com.example.osahaneat.service.impl.LoginServiceImpl;
import com.example.osahaneat.utils.JwtUtilsHelper;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginServiceImpl loginServiceImpl;
	
	@Autowired
	JwtUtilsHelper jwtUtilsHelper;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
		ResponData responData = new ResponData();
		
		if (loginServiceImpl.checkLogin(username, password)) {
			
			String token = jwtUtilsHelper.generateToken(username);
			
			responData.setData(token);
			
			responData.setUsername(username);
		
			
		}else {
			responData.setData("");
			
			responData.setSuccess(false);
		}
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SigiupRequest sigiupRequest){
		
		ResponData responData = new ResponData();
		
		responData.setData(loginServiceImpl.addUser(sigiupRequest));
		
		
		return new ResponseEntity<>(sigiupRequest, HttpStatus.OK);
	}

	
	
}
