package com.example.osahaneat.entity.key;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class KeyFavorite implements Serializable {
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="food_id")
	private Long foodId;
	
	

}
