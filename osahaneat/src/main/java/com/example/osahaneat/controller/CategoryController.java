package com.example.osahaneat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.osahaneat.dto.CategoryDTO;
import com.example.osahaneat.payload.ResponData;
import com.example.osahaneat.service.impl.CategoryServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		
		ResponData responData = new ResponData();
		
		 List<CategoryDTO> list = categoryServiceImpl.getListCategory();
		 
		 responData.setData(list);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addCategory(@RequestParam String name){
		
		ResponData responData = new ResponData();
		
		 Boolean isSuccess = categoryServiceImpl.add(name);
		 
		 responData.setData(isSuccess);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateCategory(@RequestParam Long id, @RequestParam String name){
		
		ResponData responData = new ResponData();
		
		 Boolean isSuccess = categoryServiceImpl.update(id, name);
		 
		 responData.setData(isSuccess);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
		
	}


}
