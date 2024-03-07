package com.example.osahaneat.entity;

import java.util.Date;
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

@Entity(name="users")
@Data
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="create_date")
    private Date createDate;
   
	@ManyToOne
	@JoinColumn(name="role_id")
	private Roles roles;
	
	@OneToMany(mappedBy = "users") 
	private List<RatingFood> listRatingFoods;
	
	@OneToMany(mappedBy = "users")
	private List<RatingRestaurant> listRatingRestaurants;
	
	@OneToMany(mappedBy = "users")
	private List<Orders> listOrders ;
	
	@OneToMany(mappedBy = "users")
	private List<Favorite> listFavorites ;

}
