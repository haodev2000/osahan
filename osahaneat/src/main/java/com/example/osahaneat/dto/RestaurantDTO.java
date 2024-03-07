package com.example.osahaneat.dto;

import java.util.Date;
import java.util.List;

import com.example.osahaneat.entity.MenuRestaurant;

import lombok.Data;

@Data
public class RestaurantDTO {

	private Long id;

	private String title;

	private String subtitle;

	private String image;

	private boolean isfreship;
	
	private double rate;
	
	private Date openDate;
	
	private String description;
	
	private List<CategoryDTO> lisCategoryDTOs;
	
	private List<MenuRestaurant> listMenuRestaurants;
}
