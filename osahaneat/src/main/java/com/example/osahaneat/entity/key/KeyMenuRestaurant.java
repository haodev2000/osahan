package com.example.osahaneat.entity.key;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class KeyMenuRestaurant implements Serializable {

	@Column(name ="cate_id")
	private Long cateId;
	
	@Column(name ="res_id")
	private Long resId;
	
	public KeyMenuRestaurant() {
		// TODO Auto-generated constructor stub
	}

	public KeyMenuRestaurant(Long cateId, Long resId) {
		this.cateId = cateId;
		this.resId = resId;
	}
	
	
}
