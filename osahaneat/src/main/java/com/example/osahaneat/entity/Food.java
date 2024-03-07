package com.example.osahaneat.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name="food")
@Data
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="image")
	private String image;
	
	@Column(name="time_ship")
	private String timeShip;
	
	@Column(name="is_freeship")
	private boolean is_freeship;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="description")
	private String description;
    
	@ManyToOne
	@JoinColumn(name="cate_id")
    private Category category;
	
	@OneToMany(mappedBy = "foods")
	private List<RatingFood> listRatingFoods;
	
	@OneToMany(mappedBy = "foods")
	private List<OrderDetail>  listOrderDetails;
	
	@OneToMany(mappedBy = "foods")
	private List<Favorite> listFavorites ;
	
	
	
}
