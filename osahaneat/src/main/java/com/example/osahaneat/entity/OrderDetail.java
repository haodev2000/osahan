package com.example.osahaneat.entity;

import java.util.Date;

import com.example.osahaneat.entity.key.KeyOrderDetail;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "orderdetail")
@Data
public class OrderDetail {

	@EmbeddedId
	KeyOrderDetail keys;
	
	@ManyToOne
	@JoinColumn(name="order_id", insertable = false, updatable = false)
	private Orders orders;
	
	
	@ManyToOne
	@JoinColumn(name="food_id", insertable = false, updatable = false)
    private Food foods;
	
	@Column(name="createDate")
    private Date create_date;
	
	@Column(name="price")
	private Double price;
	
}
