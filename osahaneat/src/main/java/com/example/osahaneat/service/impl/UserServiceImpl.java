package com.example.osahaneat.service.impl;

import java.util.List;

import com.example.osahaneat.dto.UsersDTO;

public interface UserServiceImpl {
	List<UsersDTO> getall();
	
	UsersDTO getDetail(Long id);
}
