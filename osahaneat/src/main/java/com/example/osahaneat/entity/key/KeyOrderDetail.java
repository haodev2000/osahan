package com.example.osahaneat.entity.key;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class KeyOrderDetail implements Serializable {

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "food_id")
	private Long foodId;

	public KeyOrderDetail() {
		// TODO Auto-generated constructor stub
	}

	public KeyOrderDetail(Long orderId, Long foodId) {
	
		this.orderId = orderId;
		this.foodId = foodId;
	}

}
