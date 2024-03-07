package com.example.osahaneat.entity;

import com.example.osahaneat.entity.key.KeyFavorite;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name="favoriteuser")
@Data
public class Favorite {

	@EmbeddedId
	KeyFavorite keys;
	
	@ManyToOne
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	private Users users;
	
	@ManyToOne
	@JoinColumn(name="food_id", insertable = false, updatable = false)
	private Food foods;
	
}
