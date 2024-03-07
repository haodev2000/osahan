package com.example.osahaneat.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name="category")
@Data
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="name_category")
	private String nameCategory;
	
	@Column(name="create_date")
	private Date createDate;
	
	@OneToMany(mappedBy = "category")
	private List<Food> listFood;
	
	@OneToMany(mappedBy = "category")
	private List<MenuRestaurant> listMenuRestaurants;
}
