package com.example.osahaneat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name="ratingfood")
@Data
public class RatingFood {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users users;
	
	@ManyToOne
	@JoinColumn(name="food_id")
	private Food foods;
	
	@Column(name="content")
	private String content;
	
	@Column(name="rate_point")
	private int ratePoint;
	
}
