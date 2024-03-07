package com.example.osahaneat.service.impl;

import com.example.osahaneat.payload.request.SigiupRequest;

public interface LoginServiceImpl {
	
	boolean checkLogin(String username, String password);
	
	boolean addUser(SigiupRequest sigiupRequest);
	
	
}
