package com.example.osahaneat.service.impl;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.osahaneat.dto.RestaurantDTO;

public interface RestaurantServiceImpl {

	boolean insertRestaurant(String title,
			String subtitle,
			 String description,
			boolean isfreship,
			String address,
			String openDate,
			MultipartFile file);
	
	List<RestaurantDTO> getListRestaurant();
	
	RestaurantDTO getDetail(Long id);
	
	boolean update(Long id,
			String title,
			String subtitle,
			 String description,
			boolean isfreship,
			String address,
			String openDate,
			MultipartFile file);
	
}
