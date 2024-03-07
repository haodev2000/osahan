package com.example.osahaneat.dto;

import lombok.Data;

@Data
public class FoodDTO {
	
	private Long id;
	
	private String title;
	
	private String image;
	
	private boolean is_freeship;
	
	private String description;
	
	private Double price;

	
}
