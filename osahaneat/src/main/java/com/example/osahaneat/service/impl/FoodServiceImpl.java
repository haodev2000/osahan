package com.example.osahaneat.service.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.osahaneat.dto.FoodDTO;

public interface FoodServiceImpl {

	boolean insertFood(@RequestParam String title,
			@RequestParam Double price,
			@RequestParam String time_ship,
			@RequestParam boolean is_freeship,
			@RequestParam Long cateId,
			@RequestParam MultipartFile file);
	
	List<FoodDTO> getListFood();
	
	List<FoodDTO> getListFood_Popular();
	
	boolean updateFood(
			@RequestParam Long id,
			@RequestParam String title,
			@RequestParam Double price,
			@RequestParam String time_ship,
			@RequestParam boolean is_freeship,
			@RequestParam Long cateId,
			@RequestParam MultipartFile file);
	
}
