package com.example.osahaneat.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity(name="roles")
@Data
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="role_name")
	private String roleName;
	
	@Column(name="create_date")
    private Date createDate;
	
	@OneToMany(mappedBy = "roles")
	private List<Users> listUser;
	
	
  
}
