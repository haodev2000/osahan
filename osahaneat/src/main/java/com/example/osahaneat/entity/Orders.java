package com.example.osahaneat.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name = "orders")
@Data
public class Orders {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;
	
	@ManyToOne
	@JoinColumn(name = "res_id")
    private Restaurant restaurants;
	
	@Column(name="create_date")
    private Date createDate;
	
	@OneToMany(mappedBy = "orders")
	private List<OrderDetail> listOrderDetails;
	
}
