package com.example.osahaneat.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UsersDTO {

	private Long id;

	private String username;

	private String password;

	private String fullname;

	private Date createDate;

}
