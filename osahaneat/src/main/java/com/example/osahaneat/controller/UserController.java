package com.example.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.osahaneat.payload.ResponData;
import com.example.osahaneat.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/user")
public class UserController {


	@Autowired
	UserServiceImpl userServiceImpl;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		ResponData responData = new ResponData();
		
		responData.setData(userServiceImpl.getall());
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
	}
	
	@GetMapping("/getDetail")
	public ResponseEntity<?> getDetail(@RequestParam Long id){
		ResponData responData = new ResponData();
		
		responData.setData(userServiceImpl.getDetail(id));
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
	}
}
