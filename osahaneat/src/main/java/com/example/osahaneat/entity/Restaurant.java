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

@Entity(name = "restaurant")
@Data
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="title")
	private String title;

	@Column(name="subtitle")
	private String subtitle;

	@Column(name="description")
	private String description;

	@Column(name="image")
	private String image;

	@Column(name="isfreship")
	private boolean isfreship;

	@Column(name="address")
	private String address;
	
	@Column(name="open_date")
	private Date openDate;
	
	@OneToMany(mappedBy = "restaurants")
	private List<RatingRestaurant> listRatingRestaurants;
	
	@OneToMany(mappedBy = "restaurants")
	private List<Orders> listOrders;
	
	@OneToMany(mappedBy = "restaurants")
	private List<MenuRestaurant> listMenuRestaurants;
	
	@OneToMany(mappedBy = "restaurants")
	private List<Promo> listPromos;
	
	
}
