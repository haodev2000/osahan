package com.example.osahaneat.payload.request;

import java.util.Date;

import lombok.Data;

@Data
public class SigiupRequest {

	private String fullname;
	private String username;
	private String password;
	private Long roleId;
	private Date createDate;
	
}
