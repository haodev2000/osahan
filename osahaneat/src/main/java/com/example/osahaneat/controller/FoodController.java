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

import com.example.osahaneat.dto.FoodDTO;
import com.example.osahaneat.payload.ResponData;
import com.example.osahaneat.service.FilesStorageServiceImpl;
import com.example.osahaneat.service.impl.FoodServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/food")
public class FoodController {

	@Autowired
	FoodServiceImpl foodServiceImpl;
	
	@Autowired
	FilesStorageServiceImpl filesStorageServiceImpl;
	
	@PostMapping("/create")
	public ResponseEntity<?> createFood(
			@RequestParam String title,
			@RequestParam Double price,
			@RequestParam String time_ship,
			@RequestParam boolean is_freeship,
			@RequestParam Long cateId,
			@RequestParam MultipartFile file){
		
		ResponData responData = new ResponData();
		
		 boolean isSuccess = foodServiceImpl.insertFood(title, price, time_ship, is_freeship, cateId, file);
		 
		 responData.setData(isSuccess);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
		
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(){
		
		ResponData responData = new ResponData();
		
		 List<FoodDTO> list = foodServiceImpl.getListFood();
		 
		 responData.setData(list);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
		
	}
	
	@GetMapping("/listPopular")
	public ResponseEntity<?> getList_Popular(){
		
		ResponData responData = new ResponData();
		
		 List<FoodDTO> list = foodServiceImpl.getListFood_Popular();
		 
		 responData.setData(list);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateFood(
			@RequestParam Long id,
			@RequestParam String title,
			@RequestParam Double price,
			@RequestParam String time_ship,
			@RequestParam boolean is_freeship,
			@RequestParam Long cateId,
			@RequestParam MultipartFile file){
		
		ResponData responData = new ResponData();
		
		 boolean isSuccess = foodServiceImpl.updateFood(id,title, price, time_ship, is_freeship, cateId, file);
		 
		 responData.setData(isSuccess);
		
		return new ResponseEntity<>(responData, HttpStatus.OK);
		
	}
	
	@GetMapping("/file/{filename:.+}")
	public ResponseEntity<?> loadFileFood(@PathVariable String filename){
		
		Resource resource= filesStorageServiceImpl.load(filename);
		
		return  ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
		
	}
}
