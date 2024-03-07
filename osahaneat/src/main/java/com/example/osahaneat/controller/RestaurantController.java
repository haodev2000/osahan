package com.example.osahaneat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.osahaneat.dto.RestaurantDTO;
import com.example.osahaneat.payload.ResponData;
import com.example.osahaneat.service.FilesStorageServiceImpl;
import com.example.osahaneat.service.impl.RestaurantServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	FilesStorageServiceImpl filesStorageServiceImpl;
	
	@Autowired
	RestaurantServiceImpl restaurantServiceImpl;
	
	@PostMapping("/create")
	public ResponseEntity<?> createRestaurant(
			@RequestParam String title,
			@RequestParam String subtitle,
			@RequestParam String description,
			@RequestParam boolean isfreship,
			@RequestParam String address,
			@RequestParam String openDate,
			@RequestParam MultipartFile file){
		
		ResponData responData = new ResponData();
		
		 boolean isSuccess = restaurantServiceImpl.insertRestaurant(title, subtitle, description, isfreship, address, openDate, file);
		 
		 responData.setData(isSuccess);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateRestaurant(
			@RequestParam Long id,
			@RequestParam String title,
			@RequestParam String subtitle,
			@RequestParam String description,
			@RequestParam boolean isfreship,
			@RequestParam String address,
			@RequestParam String openDate,
			@RequestParam MultipartFile file){
		
		ResponData responData = new ResponData();
		
		 boolean isSuccess = restaurantServiceImpl.update(id,title, subtitle, description, isfreship, address, openDate, file);
		 
		 responData.setData(isSuccess);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
		
	}
	
	@GetMapping("")
	public ResponseEntity<?> listRestaurant(){
		
		ResponData responData = new ResponData();
		
		 List<RestaurantDTO> list = restaurantServiceImpl.getListRestaurant();
		 
		 responData.setData(list);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/detail")
	public ResponseEntity<?> getDetailRestaurant(@RequestParam Long id){
		
		ResponData responData = new ResponData();
		
		RestaurantDTO restaurantDTO = restaurantServiceImpl.getDetail(id);
		 
		 responData.setData(restaurantDTO);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
		
	}
	
	@GetMapping("/file/{filename:.+}")
	public ResponseEntity<?> loadFileRestaurant(@PathVariable String filename){
		
		Resource resource= filesStorageServiceImpl.load(filename);
		
		return  ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
		
	}

}
