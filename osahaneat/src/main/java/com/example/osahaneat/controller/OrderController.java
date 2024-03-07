package com.example.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.osahaneat.payload.request.OrderRequest;
import com.example.osahaneat.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@PostMapping("")
	public ResponseEntity<?> getAll(@RequestBody OrderRequest orderRequest){
		
		return new ResponseEntity<>(orderServiceImpl.addOrder(orderRequest), HttpStatus.OK);
	}

}
