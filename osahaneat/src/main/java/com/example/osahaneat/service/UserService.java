package com.example.osahaneat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.osahaneat.dto.UsersDTO;
import com.example.osahaneat.entity.Users;
import com.example.osahaneat.responsitory.UserResponsitory;
import com.example.osahaneat.service.impl.UserServiceImpl;

@Service
public class UserService implements UserServiceImpl {
	
	@Autowired
	UserResponsitory userResponsitory;

	@Override
	public List<UsersDTO> getall() {
		List<Users> listUser = userResponsitory.findAll();

		List<UsersDTO> listDtos = new ArrayList<UsersDTO>();

		for (Users users : listUser) {

			UsersDTO u = new UsersDTO();

			u.setId(users.getId());
			u.setFullname(users.getFullname());
			u.setUsername(users.getUsername());
			u.setPassword(users.getPassword());
			u.setCreateDate(users.getCreateDate());

			listDtos.add(u);

			System.out.println(users.getFullname());
		}

		return listDtos;
	}

	@Override
	public UsersDTO getDetail(Long id) {
	
		Optional<Users> u = userResponsitory.findById(id);
		
		UsersDTO user = new UsersDTO();
		user.setId(u.get().getId());
		user.setFullname(u.get().getFullname());
		user.setUsername(u.get().getUsername());
		user.setPassword(u.get().getPassword());
		user.setCreateDate(u.get().getCreateDate());
	
		
		return user;
	}

}
