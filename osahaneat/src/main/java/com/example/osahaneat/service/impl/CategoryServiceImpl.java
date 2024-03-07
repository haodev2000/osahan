package com.example.osahaneat.service.impl;

import java.util.List;

import com.example.osahaneat.dto.CategoryDTO;

public interface CategoryServiceImpl {
	
	List<CategoryDTO> getListCategory();
	
	boolean add(String name);
	
	boolean update(Long id, String name);
	
}
