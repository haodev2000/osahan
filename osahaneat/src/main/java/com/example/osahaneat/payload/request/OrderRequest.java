package com.example.osahaneat.payload.request;

import lombok.Data;

@Data
public class OrderRequest {

	private Long user_id;
	private Long res_id;
	private Long[] food_id;
}
