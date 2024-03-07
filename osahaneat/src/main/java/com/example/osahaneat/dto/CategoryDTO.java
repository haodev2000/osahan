package com.example.osahaneat.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryDTO {
		
	private String nameCategory;
	
	private List<FoodDTO> listFood;
	
}