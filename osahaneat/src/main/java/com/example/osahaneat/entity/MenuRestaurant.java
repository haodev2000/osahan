package com.example.osahaneat.entity;

import java.util.Date;

import com.example.osahaneat.entity.key.KeyMenuRestaurant;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name="menurestaurant")
@Data
public class MenuRestaurant {

	
	@EmbeddedId
	KeyMenuRestaurant keys;
	
	@ManyToOne
	@JoinColumn(name="cate_id", insertable = false, updatable = false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="res_id", insertable = false, updatable = false)
	private Restaurant restaurants;
	
	@Column(name="create_date")
    private Date create_date;
}
